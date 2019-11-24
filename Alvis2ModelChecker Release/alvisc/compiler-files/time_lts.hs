----------------------------------------------------------------------

-- | Returns the duration for system transitions.
systemTransitionsDuration :: Int
systemTransitionsDuration = 0

----------------------------------------------------------------------

-- | Returns the duration for given transition.
transDuration :: TTransition -> Int
transDuration (STInAP   _ _ _) = systemTransitionsDuration
transDuration (STInPP   _ _ _) = systemTransitionsDuration
transDuration (STOutAP  _ _ _) = systemTransitionsDuration
transDuration (STOutPP  _ _ _) = systemTransitionsDuration
transDuration (STDelayEnd _ _) = systemTransitionsDuration
transDuration (STLoopEnd  _ _) = systemTransitionsDuration
transDuration (STInEnd    _ _) = systemTransitionsDuration
transDuration (STOutEnd   _ _) = systemTransitionsDuration
transDuration (STTime d)       = d
transDuration tr = duration (transAgent tr) (transNumber tr)

----------------------------------------------------------------------

-- | Returns the time argument for the given entry or -1 it there is no such an argument.
contextInfo2Time :: ContextInfo -> Int
contextInfo2Time (CTimer _ d)  = d
contextInfo2Time (COTimer _ d) = d
contextInfo2Time (CTimeout _)  = 0
contextInfo2Time (CSFT d)      = d
contextInfo2Time _             = -1

----------------------------------------------------------------------

-- | Changes CTimer to COTimer. COTimers are shift during multi-step firing.
switchToOldTimer :: ContextInfo -> ContextInfo
switchToOldTimer (CTimer n d) = COTimer n d
switchToOldTimer info = info

----------------------------------------------------------------------

-- | Switches all state timers to COTimer.
switchAllTimersToOld :: State -> State
switchAllTimersToOld state 
  = updateAllContextInfo (\ ci -> Set.map switchToOldTimer ci) state

----------------------------------------------------------------------

-- | Updates a context entry due to the passage of time.
updateEntry :: Int          -- ^ passage of time
            -> ContextInfo  -- ^ context entry 
            -> ContextInfo  -- ^ udpated entry
updateEntry delta (COTimer n d) 
  | delta == d = CTimeout n
  | delta < d  = CTimer n (d - delta)
updateEntry delta (CSFT d)
  | delta <= d = CSFT (d - delta)  
updateEntry _ (CTimer n 0) = CTimeout n
updateEntry _ (CNSFT d) = CSFT d
updateEntry _ entry = entry   

----------------------------------------------------------------------

-- | Updates a context information list due to the passage of time.
updateContext :: Int -> Set ContextInfo -> Set ContextInfo
updateContext delta ci = Set.map (updateEntry delta) ci

----------------------------------------------------------------------

-- | Returns True if the argument is CSFT entry, returns False otherwise.
isSFT :: ContextInfo -> Bool
isSFT (CSFT _) = True
isSFT _ = False

----------------------------------------------------------------------

-- | Returns True if the argument is CLock entry, returns False otherwise.
isLock :: ContextInfo -> Bool
isLock (CLock _) = True
isLock _ = False

----------------------------------------------------------------------

-- | Returns True if the argument contains a CLock entry, returns False otherwise.
containsLock :: Set ContextInfo -> Bool
containsLock ci = (Set.size (Set.filter isLock ci)) > 0 

----------------------------------------------------------------------

-- | Removes a CLock entry from the given context information list (set).
removeLock :: Set ContextInfo -> Set ContextInfo
removeLock ci = difference ci (Set.filter isLock ci)

----------------------------------------------------------------------

-- | Returns the argument of the CLock entry.
lockName :: ContextInfo -> Agent
lockName (CLock a) = a

----------------------------------------------------------------------

-- | Returns the name of agent that locked the given agent (second argument). 
--   If the agent is not locked, its own name is returned.
lockingAgent :: State -> Agent -> Agent
lockingAgent state agent 
  | lock == empty = agent
  | otherwise = lockName (head (toList lock))
  where n  = agentNumber agent
        ci = takeci n state
        lock = Set.filter isLock ci

----------------------------------------------------------------------

-- | Returns the name of agent that is locked by the given agent (second argument). 
--   If none agent is locked, the second argument is returned is returned.
lockedAgent :: State -> Agent -> Agent
lockedAgent state agent
  | locked == [] = agent
  | otherwise = head locked   -- there is exactly one
  where locked = [a | (a,b) <- zip agents (Prelude.map (lockingAgent state) agents), b == agent, a /= agent]

----------------------------------------------------------------------

-- | Returns True if the argument contains a CSFT entry, returns False otherwise.
containsSFT :: Set ContextInfo -> Bool
containsSFT ci = (Set.size (Set.filter isSFT ci)) > 0 

----------------------------------------------------------------------

-- | Returns the agent step-finish-time or -1 if none transition of
--   the agent is in progress in the given state.
agentSFT :: Agent -> State -> Int
agentSFT agent state 
  | sftEntry == empty = -1
  | otherwise         = sft
  where n  = agentNumber agent
        ci = takeci n state
        sftEntry = Set.filter (isSFT) ci
        sft = contextInfo2Time (head (elems sftEntry))

----------------------------------------------------------------------

-- | Returns list of integers used to determine time shift for the given agent.
--   This is auxiliary function. 
ci2time :: Mode           -- ^ Agent mode
        -> Int            -- ^ Program counter
        -> [ContextInfo]  -- ^ Current state
        -> [Int]          -- ^ List of time shifts
ci2time am pc ((CSFT d):cis)      = d : (ci2time am pc cis)
ci2time am pc ((CNSFT d):cis)     = d : (ci2time am pc cis)
ci2time am pc ((CTimer _ d):cis)  = d : (ci2time am pc cis)
ci2time am pc ((COTimer _ d):cis) = d : (ci2time am pc cis)
ci2time am pc ((CTimeout n):cis) 
  | am == W && pc == n = [0]
  | otherwise = ci2time am pc cis
ci2time am pc (_:cis) = ci2time am pc cis
ci2time _ _ [] = []

----------------------------------------------------------------------

-- | Returns list of ciShifts for selected agents in the given state.
--   The list is reduced to the important values only (e.g. the list contains
--   at most one value equal to -1 as its last element).
agentsShifts :: Int    -- ^ Number of agents (modelSize)
             -> State  -- ^ Current state
             -> [Int]  -- ^ importatnt ciShifts
agentsShifts 1 state 
  | shiftList == [] = [-1]
  | otherwise = [Prelude.minimum shiftList]
  where shiftList = ci2time (takeam 1 state) (takepc 1 state) (toList (takeci 1 state))

agentsShifts n state
  | shiftList == [] = agentsShifts (n - 1) state
  | listMin == 0    = [0]  
  | otherwise       = listMin : (agentsShifts (n - 1) state)
  where shiftList = ci2time (takeam n state) (takepc n state) (toList (takeci n state))
        listMin   = Prelude.minimum shiftList

----------------------------------------------------------------------

-- | Returns the multi-step shift based on transitions duration only.
transShift :: [TTransition] -> Int
transShift [] = -1  -- This value is returned for an empty multi-step only.
transShift [tr] = transDuration tr
transShift (tr:trs) 
  | shift == 0 = 0
  | otherwise  = min shift (transShift trs)
  where shift = transDuration tr

----------------------------------------------------------------------

-- | Returns the multi-step shift time for the given state and list of transitions.
timeShift :: State -> [TTransition] -> Int
timeShift state trs
  | agShifts == [-1] && trShift == -1 = -1   -- This value point out a dead state
  | agShifts == [-1] = trShift
  | trShift == -1    = minShift              -- The smallest non-negative value.
  | otherwise        = min trShift minShift
  where agShifts = agentsShifts modelSize state
        trShift  = transShift trs
        minShift = minimum (Prelude.filter (>= 0) agShifts)

----------------------------------------------------------------------

-- | Generates the list of succeeding states for the given state and transition 
--   fired in the state. This function takes the shift time into consideration
--   and generates only partial results. This is auxiliary function. 
transTimeFirePartial :: Int          -- ^ time shift for the multi-step
                     -> TTransition  -- ^ transition
                     -> State        -- ^ source state
                     -> [State]      -- ^ list of result states
transTimeFirePartial delta tr state
  | sft > 0   && sft == delta    = fire tr state   -- finish transition firing
  | sft == -1 && trTime == delta = fire tr state   -- finish transition firing
  | sft == -1 && trTime > delta && isAgentInOutTransition tr
    = [delContextInfo n2 (timer) (addContextInfo n2 (CLock agent1) (addContextInfo n1 (CNSFT (trTime - delta)) state))]        
  | sft == -1 && trTime > delta  = [addContextInfo n1 (CNSFT (trTime - delta)) state]     
  | isAgentInOutTransition tr && not (containsLock (takeci n2 state))
    = [delContextInfo n2 (timer)(addContextInfo n2 (CLock agent1) state)]
  | otherwise                    = [state]
  where agent1 = transAgent tr
        agent2 = sndAgent tr
        sft    = agentSFT agent1 state
        trTime = transDuration tr
        n1     = agentNumber agent1
        n2     = agentNumber agent2
        timer  = selectTimer (takepc n2 state) (takeci n2 state)

----------------------------------------------------------------------

-- | Applies the transTimeFirePartial function to the list of states.
transTimeFire :: Int          -- ^ time shift for the multi-step
              -> TTransition  -- ^ transition
              -> [State]      -- ^ list of source states
              -> [State]      -- ^ list of result states
transTimeFire delta tr states
  = concat (Prelude.map (transTimeFirePartial delta tr) states)  -- concatenates lists of states

----------------------------------------------------------------------

-- | Generates the list of succeeding states for the given list of states and multi-step 
--   fired in these states. This function takes the shift time into consideration
--   and generated only partial results. This is auxiliary function. 
timeFirePartial :: Int            -- ^ time shift for the multi-step
                -> [TTransition]  -- ^ multi-step
                -> [State]        -- ^ list of source states
                -> [State]        -- ^ list of result states
timeFirePartial delta [tr] states = transTimeFire delta tr states
timeFirePartial delta (tr:trs) states = timeFirePartial delta trs (transTimeFire delta tr states)
timeFirePartial _ [] _ = []  

----------------------------------------------------------------------

-- | Generates the list of succeeding states for the given state and multi-step 
--   fired in the state. This function takes the shift time into consideration.
timeFire :: Int -> [TTransition] -> State -> [State]
timeFire _ [STTime delta] state = [clearContextLists (updateAllContextLists delta (switchAllTimersToOld state))]
timeFire delta trs state
  = Prelude.map clearLocks (Prelude.map clearContextLists (Prelude.map (updateAllContextLists delta) unclearedNewStates))
  where unclearedNewStates = timeFirePartial delta trs [switchAllTimersToOld state]  

----------------------------------------------------------------------

-- | Provides the number of the LTS node that contains given state.
findState :: State   -- ^ model state
          -> [Node]  -- ^ list of nodes of the LTS graph
          -> Int     -- ^ return value
findState _ [] = -1
findState z ((no,state,trs):nodes) | z == state = no
                                   | otherwise  = findState z nodes  

----------------------------------------------------------------------

{-|
    Udpates the list of unprocessed nodes and the list of auxiliary
    nodes. For each state from the list of new states, the function
    checks whether the auxiliary list (list of all already generated
    nodes) already contains a node with such a state. If so, only new
    arc is added to the first node of unprocessed nodes. Otherwise, 
    a new node is added to both lists (and the corresponding arc to
    the first node of unprocessed nodes).
-}
update :: ([Node], [Node])  -- ^ list of nodes to process and auxiliary list of already generated nodes
       -> [TTransition]     -- ^ multi-step (used as the label for arsc)
       -> Int               -- ^ time shift
       -> [State]           -- ^ list of state to be added to both lists. 
       -> ([Node], [Node])  -- ^ updated lists of nodes

update (unprocNodes@((number,state,arcs):nodes), auxList) mstep delta (newState:newStates) 
  | index == -1 = update (((number,state,arcs ++ [(mstep,delta,(lastNumber+1))]):nodes) ++ [((lastNumber+1),newState,[])],
                          auxList ++ [((lastNumber+1),newState,[])]) mstep delta newStates
  | otherwise = update ((number,state,arcs ++ [(mstep,delta,index)]):nodes,auxList) mstep delta newStates
  where index = findState newState auxList
        (lastNumber,lastState,lastArcs) = last unprocNodes

update lists _ _ [] = lists

----------------------------------------------------------------------

-- | Function generates the time LTS graph. It starts with initial 
--   state as the only node of the LTS graph and the only node to proceed
--   and then builds it recursively. 

ltsgen :: [Node]          -- ^ List of unprocessed nodes. We focus on the first node in the list.
       -> [[TTransition]] -- ^ List of multi-steps enable in the first state of the unprocessed nodes.
       -> [Node]          -- ^ Auxiliary list of already generated nodes.
       -> [Node]          -- ^ Final LTS graph.
       
ltsgen unprocNodes@((number,state,arcs):nodes) (mstep:msteps) auxList = ltsgen newUnprocNodes msteps newAuxList 
  where delta = timeShift state mstep              -- time shift for the first state and first multi-step
        states = timeFire delta mstep state        -- list of new states generated for the given state and multi-step
        (newUnprocNodes,newAuxList) = update (unprocNodes,auxList) mstep delta states

-- Omit the first node if all transitions have been considered.
ltsgen (node1 : node2@(number,state,arcs) : nodes) [] auxList  
  = node1 : ltsgen (node2:nodes) (multiSteps (enableTransitions state) state) auxList    

ltsgen [node] [] _ = [node]          


-- LTS graph
lts = ltsgen [(0,s0,[])] (multiSteps (enableTransitions s0) s0) [(0,s0,[])]   

----------------------------------------------------------------------

-- | Checks whether two communication transitions are in conflict and should be
--   included into different multi-steps.
isConflict tr1 tr2
  | isInOutTransition tr1 && isInOutTransition tr2 && ((transAgent tr1 == transAgent tr2) || (sndAgent tr1 == sndAgent tr2)) = True
  | otherwise = False

----------------------------------------------------------------------

-- | Returns True if the list of communication transitions is free from conflicts. 
isConflictFree :: [TTransition] -> Bool
isConflictFree trs = and [not (isConflict tr1 tr2) | tr1 <- trs, tr2 <- trs, tr1 /= tr2]

----------------------------------------------------------------------

-- | Returns cross product of a list and a list of lists.
crossProduct :: [a] -> [[a]] -> [[a]]
crossProduct [] ys = ys
crossProduct x [y] = [x ++ y]
crossProduct x (y:ys) = (x ++ y) : (crossProduct x ys)

----------------------------------------------------------------------

-- | Returns True if the transition is not a communication transition,
--   and returns False otherwise.
isNotInOutTransition :: TTransition -> Bool
isNotInOutTransition tr = not (isInOutTransition tr)

----------------------------------------------------------------------

-- | Selects ghost transition from the given list of transitions. A ghost transition
--   is a transition that tries to communicate with an already locked agent
--   or try to timeout (STInEnd, STOutEnd) an already locked agent.
ghostTransitions :: [TTransition]  -- ^ list of transitions
                 -> State          -- ^ model state
                 -> [TTransition]  -- ^ list of ghost transitions
ghostTransitions (tr:trs) state
--  | isInOutTransition tr && (containsLock sndCI) && not (containsSFT fstCI) = tr : (ghostTransitions trs state) -- transition that just starts and tries to communicate with a locked agent
  | isInOutTransition tr && (containsLock sndCI) && lckAgent /= agent2 = tr : (ghostTransitions trs state) -- transition that is already going, but tries to communicate with a locked agent (locked by other agent)
  | isInOutTransition tr && lckAgent /= agent1 && lckAgent /= agent2 = tr : (ghostTransitions trs state) -- transition of an agent that already locked an agent1 but tries to communicate with an agent2
  | isInOutEndTransition tr && (containsLock fstCI) = tr : (ghostTransitions trs state) -- the agent is already lock so STInEnd or STOutEnd must be omitted
  | otherwise = ghostTransitions trs state
  where agent1 = transAgent tr
        agent2 = sndAgent tr
        lckAgent = lockedAgent state agent1
        fstCI = takeci (agentNumber agent1) state
        sndCI = takeci (agentNumber agent2) state
ghostTransitions [] _ = []

----------------------------------------------------------------------

-- | Function divides the list of enable transitions (possibly with conflicts
--   between them) into the list of enable multi-steps.
multiSteps :: [TTransition] -> State -> [[TTransition]]
multiSteps [] state
  | agShifts /= [-1] = [[STTime minShift]]
  | otherwise = [[]]
  where agShifts = agentsShifts modelSize state
        minShift = minimum (Prelude.filter (>= 0) agShifts)
multiSteps trs state
  | isConflictFree transitions = [completeMultiStep stubbTrans transitions]
  | otherwise = crossProduct nonCommTrans (Prelude.map (completeMultiStep stubbTrans) conflictFreeLists)
  where stubbTrans   = toList (stubbornTransitions trs)
        transitions  = removeList (ghostTransitions trs state) trs
        nonCommTrans = Prelude.filter isNotInOutTransition transitions
        commTrans    = Prelude.filter isInOutTransition transitions
        conflictFreeLists = splitCommTransitions commTrans

----------------------------------------------------------------------

-- | Function returns True if including a new transition to the list of communication
--   transitions generates at least one conflict.
isConflictTransition :: [TTransition] -> TTransition -> Bool
isConflictTransition [] _ = False
isConflictTransition (tr:trs) newTrans
  | isConflict tr newTrans = True
  | otherwise = isConflictTransition trs newTrans

----------------------------------------------------------------------

-- | Function checks whether the given multi-step is a maximal one,
--   i.e. there is no other communication transition to be included into the multi-step.
isMaximalMultiStep :: [TTransition]   -- ^ list of all communication transitions
                   -> [TTransition]   -- ^ multi-step
                   -> Bool
isMaximalMultiStep trs step 
  = and [isConflictTransition step tr | tr <- trs]  -- at least one False denotes the the multi-step is not maximal

----------------------------------------------------------------------

-- | Top level function used to split list of communication transitions
--   into conflict-free sublists.
splitCommTransitions :: [TTransition] -> [[TTransition]]
splitCommTransitions [tr1, tr2] = [[tr1], [tr2]]
splitCommTransitions trans = Prelude.filter (isMaximalMultiStep trans) (generateAllMatchings trans)

----------------------------------------------------------------------

-- | Generates all matchings for the given list of communication transitions. 
generateAllMatchings :: [TTransition] -> [[TTransition]]
generateAllMatchings [] = []
generateAllMatchings (tr:trs) = (generateMatchings [tr] trs) ++ (generateAllMatchings trs)

----------------------------------------------------------------------

-- | Generates matchings for the given initial sublist of a multi-step.
generateMatchings :: [TTransition] -> [TTransition] -> [[TTransition]]
generateMatchings step [] = [step]
generateMatchings step (tr:trs)
  | isConflictTransition step tr = generateMatchings step trs
  | otherwise = (generateMatchings (step ++ [tr]) trs) ++ (generateMatchings step trs)

----------------------------------------------------------------------

-- | The function generates the set of stubborn-transitions. This is auxiliary function used by
--   the multi-steps generation algorithm.
stubbornTransitions :: [TTransition] -> Set TTransition
stubbornTransitions ((TInAP p _ n):trs)  = insert (TIn p n) (stubbornTransitions trs)
stubbornTransitions ((TInPP p _ n):trs)  = insert (TIn p n) (stubbornTransitions trs)
stubbornTransitions ((TOutAP p _ n):trs) = insert (TOut p n) (stubbornTransitions trs)
stubbornTransitions ((TOutPP p _ n):trs) = insert (TOut p n) (stubbornTransitions trs)
stubbornTransitions ((TInF p _ n):trs)   = insert (TIn p n) (stubbornTransitions trs)
stubbornTransitions ((TOutF p _ n):trs)  = insert (TOut p n) (stubbornTransitions trs)
stubbornTransitions (_:trs) = stubbornTransitions trs
stubbornTransitions [] = empty

----------------------------------------------------------------------

-- | Returns True if the given port is used in the multi-step or return False otherwise.
isUsedInMultiStep :: Port -> [TTransition] -> Bool
isUsedInMultiStep p [] = False
isUsedInMultiStep p (tr:trs)
  | isCommTransition tr && p == fstPort tr = True
  | otherwise = isUsedInMultiStep p trs


----------------------------------------------------------------------

-- | The function uses the set of stubborn transitions to complete the given multi-step.
completeMultiStep :: [TTransition]   -- ^ stubborn transitions
                  -> [TTransition]   -- ^ set of transitions
                  -> [TTransition]   -- ^ multi-step
completeMultiStep (tr:trs) step
  | isUsedInMultiStep (fstPort tr) step = completeMultiStep trs step
  | otherwise = tr : completeMultiStep trs step
completeMultiStep [] step = step

----------------------------------------------------------------------

-- | Updates context information lists due to the passage of time.
updateAllContextLists :: Int -> State -> State
updateAllContextLists delta state = updateAllContextInfo (updateContext delta) state

----------------------------------------------------------------------

-- | Removes (CSFT 0) entries from context information lists (for all agents).
clearContextLists :: State -> State
clearContextLists state = updateAllContextInfo (delete (CSFT 0)) state

----------------------------------------------------------------------

-- | Adds a context information to the context list (set) of the n-th agent.
addContextInfo :: Int -> ContextInfo -> State -> State
addContextInfo n info = updateContextInfo n (insert info)

----------------------------------------------------------------------

-- | Removes a context information from the context list (set) of the n-th agent.
delContextInfo :: Int -> ContextInfo -> State -> State
delContextInfo n info = updateContextInfo n (delete info)

----------------------------------------------------------------------

-- | Removes CLock entry from the context information list (set) for n-th agent,
--   if the n-th agent is an active one in the running mode.
clearLock :: Int -> State -> State
clearLock n state 
  | am == X = updateContextInfo n removeLock state
  | otherwise = state
  where am = takeam n state

----------------------------------------------------------------------

-- | Removes redundant CLock entries for active agents in the running mode.
clearLocks :: State -> State 
clearLocks state = clearRedundantLocks modelSize state

----------------------------------------------------------------------

--   This is auxiliary recursive function.
clearRedundantLocks :: Int -> State -> State
clearRedundantLocks 0 state = state
clearRedundantLocks n state = clearRedundantLocks (n - 1) (clearLock n state)

----------------------------------------------------------------------

-- | Returns the CTimer, CTimeout or COTimer entry related to given statement number.
selectTimer :: Int -> Set ContextInfo -> ContextInfo
selectTimer n ci = selectTimerFromList n (toList ci)

----------------------------------------------------------------------

-- | Returns the CTimer, CTimeout or COTimer entry related to given statement number.
--   This is auxiliary funtion that searches a list of entries.

selectTimerFromList :: Int -> [ContextInfo] -> ContextInfo
selectTimerFromList _ [] = CNone
selectTimerFromList n ((CTimer k d):cis) 
  | n == k = CTimer k d
  | otherwise = selectTimerFromList n cis
selectTimerFromList n ((CTimeout k):cis) 
  | n == k = CTimeout k
  | otherwise = selectTimerFromList n cis
selectTimerFromList n ((COTimer k d):cis) 
  | n == k = COTimer k d
  | otherwise = selectTimerFromList n cis
selectTimerFromList n (_:cis) = selectTimerFromList n cis

----------------------------------------------------------------------

-- | Inserts list of context entries into the given set.
insertMany :: [ContextInfo] -> Set ContextInfo -> Set ContextInfo
insertMany [] set = set
insertMany entries set = union set (fromList entries)

----------------------------------------------------------------------

-- | Removes list of context entries from the given set.
deleteMany :: [ContextInfo] -> Set ContextInfo -> Set ContextInfo
deleteMany [] set = set
deleteMany entries set = difference set (fromList entries)

