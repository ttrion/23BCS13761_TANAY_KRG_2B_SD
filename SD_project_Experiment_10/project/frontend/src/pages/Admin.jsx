import { useEffect, useState } from "react";
import API from "../api/axios";

export default function Admin() {
  const [data, setData] = useState({});
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    API.get("/feedback/average")
      .then((res) => {
        setData(res.data);
        setLoading(false);
      })
      .catch((err) => {
        console.error("Admin fetch error:", err);
        setError("Access Denied: Admin privileges required.");
        setLoading(false);
      });
  }, []);

  if (loading) return <div className="card"><h3>Loading statistics...</h3></div>;
  if (error) return <div className="card"><h3 style={{color: "red"}}>{error}</h3></div>;

  return (
    <div className="card">
      <h2>Average Ratings</h2>
      {Object.keys(data).length === 0 ? (
        <p>No feedback submitted yet.</p>
      ) : (
        Object.entries(data).map(([key, value]) => (
          <div key={key} style={{ display: "flex", justifyContent: "space-between", margin: "10px 0", borderBottom: "1px solid #eee" }}>
            <strong>{key}:</strong>
            <span>{Number(value).toFixed(2)} / 5.0</span>
          </div>
        ))
      )}
    </div>
  );
}