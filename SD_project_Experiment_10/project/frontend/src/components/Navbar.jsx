import { Link, useNavigate } from "react-router-dom";

export default function Navbar() {
  const navigate = useNavigate();

  const role = localStorage.getItem("role");

  const logout = () => {
    localStorage.clear();
    navigate("/");
  };

  return (
    <div className="navbar">
      <Link to="/">Login</Link>
      <Link to="/register">Register</Link>

      <button onClick={logout} style={{ marginLeft: "10px" }}>
        Logout
      </button>
    </div>
  );
}