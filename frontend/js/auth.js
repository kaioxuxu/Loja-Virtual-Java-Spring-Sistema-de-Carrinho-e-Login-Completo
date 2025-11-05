// Cadastro
const registerForm = document.getElementById("registerForm");
if (registerForm) {
  registerForm.onsubmit = async (e) => {
    e.preventDefault();
    const user = {
      nome: document.getElementById("nome").value,
      email: document.getElementById("email").value,
      senha: document.getElementById("senha").value,
      cpf: document.getElementById("cpf").value,
      criptografar: document.getElementById("criptografar").checked,
    };
    const res = await fetch("http://localhost:8080/api/users/register", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(user),
    });
    if (res.ok) {
      alert("Cadastro realizado!");
      window.location = "index.html";
    } else {
      const err = await res.json();
      alert("Erro: " + err.error);
    }
  };
}

