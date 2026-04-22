import { BrowserRouter, Routes, Route } from "react-router-dom";

import Login from "./pages/Login";
import Register from "./pages/Register";
import Feedback from "./pages/Feedback";
import Admin from "./pages/Admin";

import Navbar from "./components/Navbar";
import ProtectedRoute from "./components/ProtectedRoute";

import "./index.css";
import "./app.css";

function App() {
  return (
    <BrowserRouter>
      <Navbar />

      <div className="container">
        <Routes>

          {/* PUBLIC ROUTES */}
          <Route path="/" element={<Login />} />
          <Route path="/register" element={<Register />} />

          {/* USER ROUTE (must be logged in as USER) */}
          <Route
            path="/feedback"
            element={
              <ProtectedRoute role="USER">
                <Feedback />
              </ProtectedRoute>
            }
          />

          {/* ADMIN ROUTE (must be logged in as ADMIN) */}
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