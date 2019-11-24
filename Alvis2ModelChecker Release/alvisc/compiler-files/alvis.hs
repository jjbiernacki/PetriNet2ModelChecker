module Main where

import Data.Set as Set 

----------------------------------------------------------------------

-- | Data type that represents modes of an agent.
data Mode 
  = F     -- ^ finished
  | I     -- ^ init
  | R     -- ^ ready
  | T     -- ^ taken
  | W     -- ^ waiting
  | X     -- ^ running (executing)
  deriving (Eq,Show,Ord)

----------------------------------------------------------------------

-- | Data type that represents an information list entries. DO NOT CHANGE THE ORDER OF CONSTRUCTORS.
data ContextInfo 
  = CProc  Port      -- ^ Name of called procedure.
  | CIn    Port      -- ^ Name of accessible in procedure or name of port with executed in statement.
  | COut   Port      -- ^ Name of accessible out procedure or name of port with executed out statement.
  | CTimer Int Int   -- ^ Number of timer time-units (second Int) to go by for the given statement (first Int).
  | CTimeout Int     -- ^ Timeout signal generated for the given statement.
  | CSFT   Int       -- ^ Number of time-units to the step finish time.
  | CNSFT  Int       -- ^ Number of time-units to the step finish time. This entry is used as a temporal one, while computation the result for a multi-step.
  | CLock  Agent     -- ^ Agent is blocked by an ongoing communication action of the given agent.
  | CNone            -- ^ Empty entry used by some searching functions to point out lack of result. 
  | COTimer Int Int  -- ^ Number of timer time-units (second Int) to go by for the given statement (first Int). This entry is used as a temporal one, while computation the result for a multi-step.
  deriving (Eq,Ord)

----------------------------------------------------------------------

-- | Returns the port label with omitted agent name.
portName :: String -> String    -- ^ port name
portName name = xs
  where (x:xs) = dropWhile ('.' /= ) name   

----------------------------------------------------------------------

-- | The 'ContextInfo' type show function.
instance Show ContextInfo where
  show (CProc a)     = "proc(" ++ (show a) ++ ")"
  show (CIn a)       = "in(" ++ (portName (show a)) ++ ")"
  show (COut a)      = "out(" ++ (portName (show a)) ++ ")"
  show (CTimer n d)  = "timer(" ++ (show n) ++ "," ++ (show d) ++ ")"
  show (CTimeout n)  = "timeout(" ++ (show n) ++ ")"
  show (CSFT d)      = "sft(" ++ (show d) ++ ")"
  show (CLock a)     = "lock(" ++ (show a) ++ ")"
  show _             = ""

----------------------------------------------------------------------

{-|
  Data type that represents transitions from the Haskell 
  representation point of view. Some of the transitions e.g.
  'TIn', 'TInAP', 'TInPP', 'STInAP', 'STInPP' are represented
  by the same label in the LTS graph.
-}
data TTransition 
  = TDelay     Agent Int       -- ^ delay statement, /parameters:/ agent, number of the statement.
  | TExec      Agent Int       -- ^ exec statement, /parameters:/ agent, number of the statement.
  | TExit      Agent Int       -- ^ exit statement, /parameters:/ agent, number of the statement.
  | TIf        Agent Int       -- ^ if statement, /parameters:/ agent, number of the statement.
  | TIn        Port Int        -- ^ in statement, /parameters:/ port, number of the statement.
  | TInAP      Port Port Int   -- ^ in statement - active agent calls (in statement) an output procedure, /parameters:/ input port, procedure port,  number of the statement.
  | TInPP      Port Port Int   -- ^ in statement - passive agent calls (in statement) an output procedure, /parameters:/ input port, procedure port,  number of the statement.
  | TInF       Port Port Int   -- ^ in statement - active agent finishes a communication with another active agent, /parameters:/ input port, output port,  number of the statement.
  | TJump      Agent Int       -- ^ jump statement, /parameters:/ agent, number of the statement.
  | TLoop      Agent Int       -- ^ loop statement, /parameters:/ agent, number of the statement.
  | TLoopEvery Agent Int       -- ^ loop every statement, /parameters:/ agent, number of the statement.
  | TNull      Agent Int       -- ^ null statement, /parameters:/ agent, number of the statement.
  | TOut       Port Int        -- ^ out statement - active agent initialises a communication /parameters:/ port, number of the statement.
  | TOutAP     Port Port Int   -- ^ out statement - active agent calls (out statement) an input procedure, /parameters:/ output port, procedure port,  number of the statement.
  | TOutPP     Port Port Int   -- ^ out statement - passive agent calls (out statement) an input procedure, /parameters:/ output port, procedure port,  number of the statement.
  | TOutF      Port Port Int   -- ^ out statement - active agent finishes a communication with another active agent, /parameters:/ output port, input port,  number of the statement.
  | TSelect    Agent Int       -- ^ select statement, /parameters:/ agent, number of the statement.
  | TStart     Agent Int       -- ^ start statement, /parameters:/ agent, number of the statement.
  | STInAP     Port Port Int   -- ^ System version of the 'TInAP' constructor - for wake up purposes.
  | STInPP     Port Port Int   -- ^ System version of the 'TInPP' constructor - for wake up purposes.
  | STOutAP    Port Port Int   -- ^ System version of the 'TOutAP' constructor - for wake up purposes.
  | STOutPP    Port Port Int   -- ^ System version of the 'TOutPP' constructor - for wake up purposes.
  | STDelayEnd Agent Int       -- ^ System transition activated after generation a timeout signal for the given delay statement.
  | STLoopEnd  Agent Int       -- ^ System transition activated after generation a timeout signal for the given loop every statement.
  | STInEnd    Port Int        -- ^ System transition activated after generation a timeout signal for the given non-blocking in statement.
  | STOutEnd   Port Int        -- ^ System transition activated after generation a timeout signal for the given non-blocking out statement.
  | STTime     Int             -- ^ System transition that represents passage of time.
  | TNone                      -- ^ Empty transition used by some searching functions to point out lack of result. 
  deriving (Eq,Ord)   

----------------------------------------------------------------------

-- | The 'TTransition' type show function.
instance Show TTransition where
  show (TDelay a _)      = "delay(" ++ show(a) ++ ")"
  show (TExec a _)       = "exec(" ++ show(a) ++ ")"
  show (TExit a _)       = "exit(" ++ show(a) ++ ")"
  show (TIf a _)         = "if(" ++ show(a) ++ ")"
  show (TIn a _)         = "in(" ++ show(a) ++ ")"
  show (TInAP a _ _)     = "in(" ++ show(a) ++ ")"
  show (TInPP a _ _)     = "in(" ++ show(a) ++ ")"
  show (TInF a _ _)      = "in(" ++ show(a) ++ ")"
  show (TJump a _)       = "jump(" ++ show(a) ++ ")"
  show (TLoop a _)       = "loop(" ++ show(a) ++ ")"
  show (TLoopEvery a _)  = "loop_every(" ++ show(a) ++ ")"
  show (TNull a _)       = "null(" ++ show(a) ++ ")"
  show (TOut a _)        = "out(" ++ show(a) ++ ")"
  show (TOutAP a _ _)    = "out(" ++ show(a) ++ ")"
  show (TOutPP a _ _)    = "out(" ++ show(a) ++ ")"
  show (TOutF a _ _)     = "out(" ++ show(a) ++ ")"
  show (TSelect a _)     = "select(" ++ show(a) ++ ")"
  show (TStart a _)      = "start(" ++ show(a) ++ ")"
  show (STInAP a _ _)    = "wakeup(" ++ show(a) ++ ")"
  show (STInPP a _ _)    = "wakeup(" ++ show(a) ++ ")"
  show (STOutAP a _ _)   = "wakeup(" ++ show(a) ++ ")"
  show (STOutPP a _ _)   = "wakeup(" ++ show(a) ++ ")"
  show (STDelayEnd a _)  = "timeout(" ++ show(a) ++ ")"
  show (STLoopEnd a _)   = "timeout(" ++ show(a) ++ ")"
  show (STInEnd a _)     = "timeout(" ++ show(a) ++ ")"
  show (STOutEnd a _)    = "timeout(" ++ show(a) ++ ")"
  show (STTime n)        = "time"

----------------------------------------------------------------------

-- -- | The 'TTransition' type show function -- version for model tests.
-- instance Show TTransition where
--   show (TDelay a _)      = "TDelay(" ++ show(a) ++ ")"
--   show (TExec a _)       = "TExec(" ++ show(a) ++ ")"
--   show (TExit a _)       = "TExit(" ++ show(a) ++ ")"
--   show (TIf a _)         = "TIf(" ++ show(a) ++ ")"
--   show (TIn a _)         = "TIn(" ++ show(a) ++ ")"
--   show (TInAP a b _)     = "TInAP(" ++ show(a) ++ ", " ++ show(b) ++ ")"
--   show (TInPP a b _)     = "TInPP(" ++ show(a) ++ ", " ++ show(b) ++ ")"
--   show (TInF a b _)      = "TInF(" ++ show(a) ++ ", " ++ show(b) ++ ")"
--   show (TJump a _)       = "TJump(" ++ show(a) ++ ")"
--   show (TLoop a _)       = "TLoop(" ++ show(a) ++ ")"
--   show (TLoopEvery a _)  = "TLoopEvery(" ++ show(a) ++ ")"
--   show (TNull a _)       = "TNull(" ++ show(a) ++ ")"
--   show (TOut a _)        = "TOut(" ++ show(a) ++ ")"
--   show (TOutAP a b _)    = "TOutAP(" ++ show(a) ++ ", " ++ show(b) ++ ")"
--   show (TOutPP a b _)    = "TOutPP(" ++ show(a) ++ ", " ++ show(b) ++ ")"
--   show (TOutF a b _)     = "TOutF(" ++ show(a) ++ ", " ++ show(b) ++ ")"
--   show (TSelect a _)     = "TSelect(" ++ show(a) ++ ")"
--   show (TStart a _)      = "TStart(" ++ show(a) ++ ")"
--   show (STInAP a b _)    = "STInAP(" ++ show(a) ++ ", " ++ show(b) ++ ")"
--   show (STInPP a b _)    = "STInPP(" ++ show(a) ++ ", " ++ show(b) ++ ")"
--   show (STOutAP a b _)   = "STOutAP(" ++ show(a) ++ ", " ++ show(b) ++ ")"
--   show (STOutPP a b _)   = "STOutPP(" ++ show(a) ++ ", " ++ show(b) ++ ")"
--   show (STDelayEnd a _)  = "STDelayEnd(" ++ show(a) ++ ")"
--   show (STLoopEnd a _)   = "STLoopEnd(" ++ show(a) ++ ")"
--   show (STInEnd a _)     = "STInEnd(" ++ show(a) ++ ")"
--   show (STOutEnd a _)    = "STOutEnd(" ++ show(a) ++ ")"
--   show (STTime n)        = "STTime(" ++ show(n) ++ ")"

----------------------------------------------------------------------

-- | Returns the agent name for the given transition.
transAgent :: TTransition -> Agent
transAgent (TDelay     agent _ ) = agent
transAgent (TExec      agent _ ) = agent
transAgent (TExit      agent _ ) = agent
transAgent (TIf        agent _ ) = agent
transAgent (TIn        port _  ) = agentName port
transAgent (TInAP      port _ _) = agentName port
transAgent (TInPP      port _ _) = agentName port
transAgent (TInF       port _ _) = agentName port
transAgent (TJump      agent _ ) = agent
transAgent (TLoop      agent _ ) = agent
transAgent (TLoopEvery agent _ ) = agent
transAgent (TNull      agent _ ) = agent
transAgent (TOut       port _  ) = agentName port
transAgent (TOutAP     port _ _) = agentName port
transAgent (TOutPP     port _ _) = agentName port
transAgent (TOutF      port _ _) = agentName port
transAgent (TSelect    agent _ ) = agent
transAgent (TStart     agent _ ) = agent
transAgent (STInAP     port _ _) = agentName port
transAgent (STInPP     port _ _) = agentName port
transAgent (STOutAP    port _ _) = agentName port
transAgent (STOutPP    port _ _) = agentName port
transAgent (STDelayEnd agent _ ) = agent
transAgent (STLoopEnd  agent _ ) = agent
transAgent (STInEnd    port _  ) = agentName port
transAgent (STOutEnd   port _  ) = agentName port

----------------------------------------------------------------------

-- | Returns the corresponding statement number for the givem transition.
transNumber :: TTransition -> Int
transNumber (TDelay     _ no  ) = no
transNumber (TExec      _ no  ) = no
transNumber (TExit      _ no  ) = no
transNumber (TIf        _ no  ) = no
transNumber (TIn        _ no  ) = no
transNumber (TInAP      _ _ no) = no
transNumber (TInPP      _ _ no) = no
transNumber (TInF       _ _ no) = no
transNumber (TJump      _ no  ) = no
transNumber (TLoop      _ no  ) = no
transNumber (TLoopEvery _ no  ) = no
transNumber (TNull      _ no  ) = no
transNumber (TOut       _ no  ) = no
transNumber (TOutAP     _ _ no) = no
transNumber (TOutPP     _ _ no) = no
transNumber (TOutF      _ _ no) = no
transNumber (TSelect    _ no  ) = no
transNumber (TStart     _ no  ) = no
transNumber (STInAP     _ _ no) = no
transNumber (STInPP     _ _ no) = no
transNumber (STOutAP    _ _ no) = no
transNumber (STOutPP    _ _ no) = no
transNumber (STDelayEnd _ no  ) = no
transNumber (STLoopEnd  _ no  ) = no
transNumber (STInEnd    _ no  ) = no
transNumber (STOutEnd   _ no  ) = no

----------------------------------------------------------------------

-- | Returns the position of the agent in the agents list. Returns -1 if 
--   agent with the given name does not exist.
agentNumber :: Agent -> Int
agentNumber agent 
  | positions == [] = -1
  | otherwise       = head positions
  where positions = [i | (a,i) <- zip agents [1..modelSize], a == agent]

----------------------------------------------------------------------

-- | Removes all occurrences of the element from the list.
removeElem :: (Eq a) => a    -- ^ element to be removed
                     -> [a]  -- ^ input list
                     -> [a]  -- ^ output list
removeElem y [] = []
removeElem y (x:xs) | y == x    = removeElem y xs
                | otherwise = x : removeElem y xs   

----------------------------------------------------------------------

-- | Removes all occurrences of elements belonging to the second list
--   from the first list.
removeList :: (Eq a) => [a]  -- ^ elements to be removed
                     -> [a]  -- ^ input list
                     -> [a]  -- ^ output list
removeList [] xs     = xs
removeList [y] xs    = removeElem y xs
removeList (y:ys) xs = removeList ys (removeElem y xs)

----------------------------------------------------------------------

-- | Returns True if the argument is CProc entry, returns False otherwise.
isProc :: ContextInfo -> Bool
isProc (CProc _) = True
isProc _ = False

----------------------------------------------------------------------

-- | Returns the name of agent, which port is the CProc entry argument.
procAgent :: ContextInfo -> Agent
procAgent (CProc port) = agentName port

----------------------------------------------------------------------

-- | Search a context information list for a 'CProc' with a name of 
--   the agent (first parameter) procedure.
--   Returns /CProc None/, if such an entry does not exist.
findProc :: Agent             -- ^ agent
         -> Set ContextInfo   -- ^ context information list
         -> ContextInfo       -- ^ return 'CProc' entry
findProc agent ci
  | ci /= empty && isProc info && procAgent info == agent = info 
  | otherwise = CProc None
  where info = head (elems ci) -- CProc (if any) is the first element.

----------------------------------------------------------------------

-- | Returns True if the context list does not contain a 'CProc' entry,
--   and returns False otherwise
procFree :: Set ContextInfo -> Bool
procFree ci
  | ci == empty = True
  | otherwise   = not (isProc info)
  where info = head (elems ci) -- CProc (if any) is the first element.

----------------------------------------------------------------------

-- | Returns the first port of the communication transition.
fstPort :: TTransition -> Port
fstPort (TInAP   port _ _) = port
fstPort (TInPP   port _ _) = port
fstPort (TInF    port _ _) = port
fstPort (TOutAP  port _ _) = port 
fstPort (TOutPP  port _ _) = port
fstPort (TOutF   port _ _) = port
fstPort (STInAP  port _ _) = port
fstPort (STInPP  port _ _) = port
fstPort (STOutAP port _ _) = port
fstPort (STOutPP port _ _) = port
fstPort (TIn  port _) = port
fstPort (TOut port _) = port
fstPort (STInEnd  port _) = port
fstPort (STOutEnd port _) = port

----------------------------------------------------------------------

-- | Returns the second port of the communication transition.
sndPort :: TTransition -> Port
sndPort (TInAP   _ port _) = port
sndPort (TInPP   _ port _) = port
sndPort (TInF    _ port _) = port
sndPort (TOutAP  _ port _) = port 
sndPort (TOutPP  _ port _) = port
sndPort (TOutF   _ port _) = port
sndPort (STInAP  _ port _) = port
sndPort (STInPP  _ port _) = port
sndPort (STOutAP _ port _) = port
sndPort (STOutPP _ port _) = port

----------------------------------------------------------------------

-- | Returns the second agent of the communication transition.
sndAgent :: TTransition -> Agent
sndAgent tr = agentName (sndPort tr)

----------------------------------------------------------------------

-- | Returns True if the transition is a communication in-out transition,
--   and returns False otherwise.
isInOutTransition :: TTransition -> Bool
isInOutTransition (TInAP _ _ _)   = True
isInOutTransition (TInPP _ _ _)   = True
isInOutTransition (TInF _ _ _)    = True
isInOutTransition (TOutAP _ _ _)  = True
isInOutTransition (TOutPP _ _ _)  = True
isInOutTransition (TOutF _ _ _)   = True
isInOutTransition (STInAP _ _ _)  = True
isInOutTransition (STInPP _ _ _)  = True
isInOutTransition (STOutAP _ _ _) = True
isInOutTransition (STOutPP _ _ _) = True
isInOutTransition _               = False

----------------------------------------------------------------------

-- | Returns True if the transition is a communication transition,
--   and returns False otherwise.
isCommTransition :: TTransition -> Bool
isCommTransition (TIn _ _)  = True
isCommTransition (TOut _ _) = True
isCommTransition (TInAP _ _ _)   = True
isCommTransition (TInPP _ _ _)   = True
isCommTransition (TInF _ _ _)    = True
isCommTransition (TOutAP _ _ _)  = True
isCommTransition (TOutPP _ _ _)  = True
isCommTransition (TOutF _ _ _)   = True
isCommTransition (STInAP _ _ _)  = True
isCommTransition (STInPP _ _ _)  = True
isCommTransition (STOutAP _ _ _) = True
isCommTransition (STOutPP _ _ _) = True
isCommTransition _               = False

----------------------------------------------------------------------

-- | Returns True if the transition is a communication in-out transition,
--   and returns False otherwise. The function returns False for system transitions.
isAgentInOutTransition  :: TTransition -> Bool
isAgentInOutTransition (TInAP _ _ _)   = True
isAgentInOutTransition (TInPP _ _ _)   = True
isAgentInOutTransition (TInF _ _ _)    = True
isAgentInOutTransition (TOutAP _ _ _)  = True
isAgentInOutTransition (TOutPP _ _ _)  = True
isAgentInOutTransition (TOutF _ _ _)   = True
isAgentInOutTransition _               = False

----------------------------------------------------------------------

-- | Returns True if the transition is a system dropping communication transition,
--   and returns False otherwise. 
isInOutEndTransition  :: TTransition -> Bool
isInOutEndTransition (STInEnd  _ _)   = True
isInOutEndTransition (STOutEnd _ _)   = True
isInOutEndTransition _               = False

----------------------------------------------------------------------

-- | Removes from the input list all communication transitions that
-- use the given port as the first argument.
removeTransitions1 :: Port             -- ^ port
                   -> [TTransition]    -- ^ input list of transitions
                   -> [TTransition]    -- ^ output list of transitions
removeTransitions1 _ [] = []
removeTransitions1 port (tr:trs) | isInOutTransition tr && port == fstPort tr = removeTransitions1 port trs
                                 | otherwise = tr : removeTransitions1 port trs

----------------------------------------------------------------------

-- | Removes from the input list all communication transitions that
-- use the given port as the second argument.
removeTransitions2 :: Port             -- ^ port
                   -> [TTransition]    -- ^ input list of transitions
                   -> [TTransition]    -- ^ output list of transitions
removeTransitions2 _ [] = []
removeTransitions2 port (tr:trs) | isInOutTransition tr && port == sndPort tr = removeTransitions2 port trs
                                 | otherwise = tr : removeTransitions2 port trs

----------------------------------------------------------------------

-- | Removes from the input list all communication transitions that
-- use the given port
removeTransitions :: Port             -- ^ port
                  -> [TTransition]    -- ^ input list of transitions
                  -> [TTransition]    -- ^ output list of transitions
removeTransitions port trs = removeTransitions2 port (removeTransitions1 port trs) 

----------------------------------------------------------------------

-- | Returns value of the lowest priority. The highest priority in Alvis is equal to 0.
--   The lowest priority in Alvis is equal to 9.
minPriority :: Int
minPriority = 9

----------------------------------------------------------------------

-- Searches for the number of agent that called a procedure of k-th agent (in state s).
searchProc :: Int -> Int -> State -> Int
searchProc k n state
  | n > modelSize = 0
  | (findProc agent (takeci n state)) /= CProc None = n
  | otherwise = searchProc k (n + 1) state
  where agent = agents!!(k - 1)  -- name of agent number k

----------------------------------------------------------------------

-- | Checks whether the context (agent) of k-th agent is in X mode (in state s).
xContext :: Int   -- ^ number of the agent
         -> State   -- ^ model state
         -> Bool    -- ^ return value
xContext k state
  | takeam k state == X = True
  | n /= 0 && takeam n state == X = True
  | n /= 0 && takeam n state == T = xContext n state
  | otherwise = False
  where n = searchProc k 1 state  -- number of agent that called procedure of k-th agent

----------------------------------------------------------------------

-- | Checks whether the context (agent) of k-th agent is in W mode (in state s).
wContext :: Int   -- ^ number of the agent
         -> State   -- ^ model state
         -> Bool    -- ^ return value
wContext k state
  | takeam k state == W = True
  | n /= 0 && takeam n state == W = True
  | n /= 0 && takeam n state == T = wContext n state
  | otherwise = False
  where n = searchProc k 1 state  -- number of agent that called procedure of k-th agent

----------------------------------------------------------------------

-- | Provides the list of all transition potentially enable in the given state.
enableInState :: State -> [TTransition]
enableInState state = Prelude.foldr (++) [] (Prelude.map (enable state) agents)

----------------------------------------------------------------------

-- | Checks whether the first list is a prefix of the second list.
isPrefix :: Ord a => [a] -> [a] -> Bool
isPrefix [] _ = True
isPrefix (_:_) [] = False
isPrefix (x:xs) (y:ys) = (x == y) && isPrefix xs ys

----------------------------------------------------------------------

-- | Drops the given prefix (first list) from the second list.
dropPrefix :: Ord a => [a] -> [a] -> [a]
dropPrefix xs ys
  | isPrefix xs ys = drop k ys
  | otherwise      = ys
  where k = length xs
