
----------------------------------------------------------------------

-- | Prints the LTS graph using the DOT format.
printDOT :: [Node] -> String
printDOT ltsGraph = "digraph G {\n\t rankdir=TB;\n" 
	++ insertEscapeChar (dumpDOTNodes ltsGraph) ++ dumpDOTTrans ltsGraph ++ "}\n"

----------------------------------------------------------------------

-- | Prints the LTS graph arcs using the DOT format.
dumpDOTTrans :: [Node] -> String
dumpDOTTrans [] = []
dumpDOTTrans (node:nodes) = dumpSingleTrans node ++ dumpDOTTrans nodes

----------------------------------------------------------------------

-- | Prints list of arcs for the given node (DOT format).
dumpSingleTrans :: Node -> String
dumpSingleTrans (number,_,arcs) = dumpTR (number, arcs)

----------------------------------------------------------------------

-- | Recursive function for printing a list of arcs for the given node.
dumpTR :: (Int,[([TTransition],Int,Int)]) -> String
dumpTR (_,[]) = []
dumpTR (source,(transitions,time,destination):arcs) 
  = "\t" ++ (show source) ++ " -> " ++ (show destination) 
  ++ " [label = \"{" ++ (dumpTRList transitions) ++ "}/" ++ (show time) ++ "\"];\n"
  ++ dumpTR (source,arcs)

----------------------------------------------------------------------

-- | Recursive function for printing a list of parallel transitions and time.
dumpTRList :: [TTransition] -> String
dumpTRList [tr] = show tr
dumpTRList (tr:trs) = show tr ++ "," ++ (dumpTRList trs)  

----------------------------------------------------------------------

-- | Inserts escape characters to the string representaion of graph nodes (DOT format).
insertEscapeChar :: String -> String
insertEscapeChar (x:xs)
  | isPrefix "label = \"(" (x:xs) = "label = \"(" ++ (insertEscapeChar (drop 10 (x:xs)))
  | isPrefix ")\" shape=box" (x:xs) = ")\" shape=box" ++ (insertEscapeChar (drop 12 (x:xs)))
  | x =='\"' = "\\\"" ++ (insertEscapeChar xs)
  | otherwise = x : (insertEscapeChar xs)
insertEscapeChar [] = []
