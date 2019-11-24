----------------------------------------------------------------------

-- | Provides the number of the LTS node that contains given state.
findState :: State   -- ^ model state
          -> [Node]  -- ^ list of nodes of the LTS graph
          -> Int     -- ^ return value
findState _ [] = -1
findState z ((no,state,trs):nodes) | z == state = no
                                   | otherwise = findState z nodes  

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
       -> TTransition       -- ^ fired transition (used as the label for arsc)
       -> [State]           -- ^ list of state to be added to both lists. 
       -> ([Node], [Node])  -- ^ updated lists of nodes

update (unprocNodes@((number,state,arcs):nodes), auxList) tr (newState:newStates) 
  | index == -1 = update (((number,state,arcs ++ [(tr,(lastNumber+1))]):nodes) ++ [((lastNumber+1),newState,[])],
                          auxList ++ [((lastNumber+1),newState,[])]) tr newStates
  | otherwise = update ((number,state,arcs ++ [(tr,index)]):nodes,auxList) tr newStates
  where index = findState newState auxList
        (lastNumber,lastState,lastArcs) = last unprocNodes

update lists _ [] = lists

----------------------------------------------------------------------

-- | Function generates the LTS graph. It starts with initial 
--   state as the only node of the LTS graph and the only node toproceed
--   and then builds it recursively. 

ltsgen :: [Node]          -- ^ List of unprocessed nodes. We focus on the first node in the list.
       -> [TTransition]   -- ^ List of transitions enable in the first state of the unprocessed nodes.
       -> [Node]          -- ^ Auxiliary list of already generated nodes.
       -> [Node]          -- ^ Final LTS graph.
       
ltsgen unprocNodes@((number,state,arcs):nodes) (tr:trs) auxList = ltsgen newUnprocNodes trs newAuxList 
  where states = fire tr state
        (newUnprocNodes,newAuxList) = update (unprocNodes,auxList) tr states

-- Omit the first node if all transitions have been considered.
ltsgen (node1 : node2@(number,state,arcs) : nodes) [] auxList  
  = node1 : ltsgen (node2:nodes) (enableTransitions state) auxList    

ltsgen [node] [] _ = [node]          


-- | LTS graph.
lts = ltsgen [(0,s0,[])] (enableTransitions s0) [(0,s0,[])]

----------------------------------------------------------------------
