import React from 'react';
import ReactDOM from 'react-dom';
import MontyHall from './MontyHall';
import { ToastContainer } from 'react-toastify';

ReactDOM.render(
  <React.StrictMode>
    <ToastContainer/>
    <MontyHall />
  </React.StrictMode>,
  document.getElementById('root')
);
