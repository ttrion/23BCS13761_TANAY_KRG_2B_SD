import { useState } from "react";
import API from "../api/axios";

export default function Register() {
  const [data, setData] = useState({ username: "", password: "" });

  const handleRegister = async () => {
    await API.post("/auth/register", data);
    alert("Registered successfully!");
    window.location.href = "/";
  };

  return (
    <div className="card">
      <h2>Register</h2>

      <input placeholder="Username"
        onChange={e => setData({ ...data, username: e.target.value })} />

      <input type="password" placeholder="Password"
        onChange={e => setData({ ...data, password: e.target.value })} />

      <button onClick={handleRegister}>Register</button>
    </div>
  );
}