window.addEventListener("load", function () {
  const formulario = document.querySelector("#update_turno_form");

  // Función para obtener y llenar la lista de pacientes desde la API
  function obtenerPacientes() {
    fetch("/pacientes")
      .then((response) => response.json())
      .then((data) => {
        const selectPaciente = document.querySelector("#paciente");
        selectPaciente.innerHTML = ""; // Limpiar opciones actuales
        data.forEach((paciente) => {
          const option = document.createElement("option");
          option.value = paciente.id;
          option.textContent = `${paciente.nombre} ${paciente.apellido}`;
          selectPaciente.appendChild(option);
        });
      })
      .catch((error) => {
        console.error("Error obteniendo pacientes:", error);
        alert("Error: No se pudo cargar la lista de pacientes.");
      });
  }

  // Función para obtener y llenar la lista de odontólogos desde la API
  function obtenerOdontologos() {
    fetch("/odontologos")
      .then((response) => response.json())
      .then((data) => {
        const selectOdontologo = document.querySelector("#odontologo");
        selectOdontologo.innerHTML = ""; // Limpiar opciones actuales
        data.forEach((odontologo) => {
          const option = document.createElement("option");
          option.value = odontologo.id;
          option.textContent = `${odontologo.nombre} ${odontologo.apellido}`;
          selectOdontologo.appendChild(option);
        });
      })
      .catch((error) => {
        console.error("Error obteniendo odontólogos:", error);
        alert("Error: No se pudo cargar la lista de odontólogos.");
      });
  }

  // Cargar lista de pacientes y odontólogos al cargar la página
  obtenerPacientes();
  obtenerOdontologos();

  formulario.addEventListener("submit", function (event) {

    const formData = {
      id: document.querySelector("#id").value,
      paciente: {
        id: document.querySelector("#paciente").value,
      },
      odontologo: {
        id: document.querySelector("#odontologo").value,
      },
      fecha: document.querySelector("#fecha").value,
    };


    const url = "/turnos";
    const settings = {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(formData),
    };
    fetch(url, settings).then((response) => response.json());
  });
});


const findBy =(id) => {
  const url = "/turnos" + "/" + id;
  const settings = {
    method: "GET",
  };
  fetch(url, settings)
    .then((response) => response.json())
    .then((turno) => {
    console.log({turno})
      document.querySelector("#id").value = turno.id;
      document.querySelector("#paciente").value = turno.pacienteId;
      document.querySelector("#odontologo").value = turno.odontologoId;
       document.querySelector("#fecha").value = turno.fecha;

      document.querySelector("#div_turno_updating").style.display = "block";
    })
    .catch((error) => {
      alert("Error: " + error);
    });
}