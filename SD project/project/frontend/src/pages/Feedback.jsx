import { useState, useEffect } from "react";
import API from "../api/axios";

const subjects = ["SD", "FS", "NM", "SS", "Aptitude", "Cloud"];

export default function Feedback() {
  const [ratings, setRatings] = useState({
    SD: 0, FS: 0, NM: 0, SS: 0, Aptitude: 0, Cloud: 0
  });
  const [submitted, setSubmitted] = useState(false);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    API.get("/feedback/status")
      .then((res) => setSubmitted(res.data))
      .catch((err) => console.error(err))
      .finally(() => setLoading(false));
  }, []);

  const handleStar = (subject, value) => {
    setRatings((prev) => ({
      ...prev,
      [subject]: value,
    }));
  };

  const submit = async () => {
    try {
      const payload = {
        sd: parseInt(ratings.SD),
        fs: parseInt(ratings.FS),
        nm: parseInt(ratings.NM),
        ss: parseInt(ratings.SS),
        aptitude: parseInt(ratings.Aptitude),
        cloud: parseInt(ratings.Cloud),
      };

      await API.post("/feedback", payload);
      alert("Feedback submitted successfully!");
      setSubmitted(true);

    } catch (err) {
      console.error(err.response?.data || err.message);
      alert("Failed to submit feedback. Ensure all subjects are rated.");
    }
  };

  if (loading) return <h3>Loading...</h3>;
  if (submitted) return <div className="card"><h3>Already submitted. Thank you!</h3></div>;

  return (
    <div className="card" style={{ width: "450px" }}>
      <h2>Give Feedback</h2>
      {subjects.map((sub) => (
        <div key={sub} style={{ marginBottom: "15px", textAlign: "left" }}>
          <span style={{ fontWeight: "bold", display: "inline-block", width: "100px" }}>{sub}:</span>
          {[1, 2, 3, 4, 5].map((num) => (
            <span
              key={num}
              onClick={() => handleStar(sub, num)}
              style={{
                cursor: "pointer",
                fontSize: "24px",
                color: ratings[sub] >= num ? "#ffc107" : "#e4e5e9",
                marginRight: "5px"
              }}
            >
              ★
            </span>
          ))}
        </div>
      ))}
      <button onClick={submit} style={{ marginTop: "20px" }}>Submit Feedback</button>
    </div>
  );
}