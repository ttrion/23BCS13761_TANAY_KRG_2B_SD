import { useState } from "react";
import API from "../api/axios";

export default function Login() {
  const [data, setData] = useState({ username: "", password: "" });

  const handleLogin = async () => {
    try {
      const res = await API.post("/auth/login", data);

      if (!res.data?.token) {
        alert("Login failed");
        return;
      }

      localStorage.setItem("token", res.data.token);
      localStorage.setItem("role", res.data.role);

      // route based on role
      if (res.data.role === "ADMIN") {
        window.location.href = "/admin";
      } else {
        window.location.href = "/feedback";
      }

    } catch (err) {
      console.error(err);
      alert("Invalid credentials");
    }
  };

  return (
    <div className="card">
      <h2>Login</h2>

      <input
        placeholder="Username"
        value={data.username}
        onChange={(e) =>
          setData({ ...data, username: e.target.value })
        }
      />

      <input
        type="password"
        placeholder="Password"
        value={data.password}
        onChange={(e) =>
          setData({ ...data, password: e.target.value })
        }
      />

      <button onClick={handleLogin}>Login</button>

      <p>Admin Login → admin / admin123</p>
    </div>
  );
}