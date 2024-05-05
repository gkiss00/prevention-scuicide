import { configureStore } from '@reduxjs/toolkit'
import authContextReducer  from './AuthContext'

export default configureStore({
    reducer: {
        authContext: authContextReducer
    }
})