import { createSlice } from '@reduxjs/toolkit'

export const authContext = createSlice({
  name: 'authContext',
  initialState: {
    token: null,
    userRole: null,
    userName: null,
    userId: null,
    homeValue:0,
    editableCommunication: null,
  },
  reducers: {
    setToken: (state, action) => {
      state.token = action.payload
    },

    setUserRole: (state, action) => {
      state.userRole = action.payload
    },

    setUserName: (state, action) => {
      state.userName = action.payload
    },

    setUserId: (state, action) => {
      state.userId = action.payload
    },

    setHomeValue: (state, action) => {
      state.homeValue = action.payload
    },

    setEditableCommunication: (state, action) => {
      state.editableCommunication = action.payload
    },
  },
})

// Action creators are generated for each case reducer function
export const { setToken, setUserRole, setUserName, setUserId, setHomeValue, setEditableCommunication } = authContext.actions

export default authContext.reducer