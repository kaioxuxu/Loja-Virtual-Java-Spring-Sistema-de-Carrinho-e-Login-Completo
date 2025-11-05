// Login
const loginForm = document.getElementById("loginForm");
if (loginForm) {
  loginForm.onsubmit = async (e) => {
    e.preventDefault();
    const body = {
      cpf: document.getElementById("cpf").value,
      senha: document.getElementById("senha").value,
    };
    const res = await fetch("http://localhost:8080/api/users/login", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(body),
    });
    if (res.ok) {
      const user = await res.json();
      sessionStorage.setItem("user", JSON.stringify(user));
      window.location = "store.html";
    } else {
      const err = await res.json();
      alert("Erro: " + err.error);
    }
  };
}
