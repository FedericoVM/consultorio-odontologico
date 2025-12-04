function toggleMenor() {
    // Obtenemos los elementos
    var radioMenor = document.getElementById('radioMenor');
    var seccionMenor = document.getElementById('seccion-menor-cargo');
    var inputTelefonoPaciente = document.getElementById('telefono-paciente');

    // Inputs del tutor (para ponerles o quitarles 'required' o limpiarlos si quieres)
    // Seleccionamos todos los inputs dentro del div del tutor
    var inputsTutor = seccionMenor.getElementsByTagName('input');

    if (radioMenor.checked) {
        // CASO: ES MENOR

        // 1. Mostrar la sección del tutor (quitamos d-none)
        seccionMenor.classList.remove('d-none');

        // 2. Desactivar el teléfono del paciente
        inputTelefonoPaciente.value = ""; // Limpiamos valor por si acaso
        inputTelefonoPaciente.disabled = true;
        inputTelefonoPaciente.placeholder = "No aplica (es menor)";

        // 3. Activar los inputs del tutor (por si estaban disabled)
        for (var i = 0; i < inputsTutor.length; i++) {
            inputsTutor[i].disabled = false;
        }

    } else {
        // CASO: ES MAYOR (Adulto)

        // 1. Ocultar la sección del tutor (agregamos d-none)
        seccionMenor.classList.add('d-none');

        // 2. Activar el teléfono del paciente
        inputTelefonoPaciente.disabled = false;
        inputTelefonoPaciente.placeholder = "Teléfono Personal";

        // 3. Desactivar los inputs del tutor para que NO se envíen al Servlet vacíos
        for (var i = 0; i < inputsTutor.length; i++) {
            inputsTutor[i].disabled = true;
        }
    }
}

// Ejecutar al cargar la página para asegurar el estado correcto
window.onload = toggleMenor;


