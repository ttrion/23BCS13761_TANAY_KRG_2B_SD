const ADMIN_USER = {
  username: "Admin",
  password: "12345",
  role: "ADMIN",
};

export const getUsers = () =>
  JSON.parse(localStorage.getItem("users") || "[]");

export const saveUsers = (users) =>
  localStorage.setItem("users", JSON.stringify(users));

export const getFeedback = () =>
  JSON.parse(localStorage.getItem("feedback") || "[]");

export const saveFeedback = (data) =>
  localStorage.setItem("feedback", JSON.stringify(data));

export const ensureAdmin = () => {
  const users = getUsers();
  if (!users.find((u) => u.username === ADMIN_USER.username)) {
    users.push(ADMIN_USER);
    saveUsers(users);
  }
};