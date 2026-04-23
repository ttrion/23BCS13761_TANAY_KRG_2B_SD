import { BrowserRouter, Routes, Route } from "react-router-dom";

import Login from "./pages/Login";
import Register from "./pages/Register";
import Feedback from "./pages/Feedback";
import Admin from "./pages/Admin";

import Navbar from "./components/Navbar";
import ProtectedRoute from "./components/ProtectedRoute";

import "./index.css";
import "./app.css";

import {
  getUsers,
  saveUsers,
  getFeedback,
  saveFeedback,
  ensureAdmin,
} from "./utils/Utils";

function App() {
  return (
    <BrowserRouter>
      <Navbar />

      <div className="container">
        <Routes>

          <Route path="/" element={<Login />} />
          <Route path="/register" element={<Register />} />

          <Route
            path="/feedback"
            element={
              <ProtectedRoute role="USER">
                <Feedback />
              </ProtectedRoute>
            }
          />

          <Route
            path="/admin"
            element={
              <ProtectedRoute role="ADMIN">
                <Admin />
              </ProtectedRoute>
            }
          />

        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
