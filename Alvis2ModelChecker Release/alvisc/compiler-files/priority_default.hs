----------------------------------------------------------------------

-- | Provides the list of all transitions enable in the given state.
enableTransitions :: State -> [TTransition]
enableTransitions = enableInState
