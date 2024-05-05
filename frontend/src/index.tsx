import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import reportWebVitals from './reportWebVitals';
import { Provider } from 'react-redux';
import store from './context/store';
import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import SignIn from './component/signIn';
import MyStats from './component/stat/myStats';
import Home from './component/Home';
import CommunicationForm from './component/communication/CommunicationForm';
import EditCommunicationForm from './component/communication/EditCommunicationForm';
import CreateDocumentForm from './component/document/CreateDocumentForm';

const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);

const router = createBrowserRouter([
  {
    path: "/",
    element: <SignIn></SignIn>,
  },
  {
    path: "/myStats",
    element: <MyStats></MyStats>,
  },
  {
    path: "/home",
    element: <Home></Home>,
  },
  {
    path: "/communications/add",
    element: <CommunicationForm></CommunicationForm>,
  },
  {
    path: "/communications/edit",
    element: <EditCommunicationForm></EditCommunicationForm>,
  },
  {
    path: "/documents/add",
    element: <CreateDocumentForm></CreateDocumentForm>,
  },
]);

root.render(
  <React.StrictMode>
    <Provider store={store}>
      <RouterProvider router={router} />
    </Provider>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
