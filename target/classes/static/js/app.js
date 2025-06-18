// URL base de la API
const API_URL = '/api/computadoras';

// Cargar computadoras cuando la página se carga
document.addEventListener('DOMContentLoaded', function() {
    cargarComputadoras();
});

// Función para cargar todas las computadoras
async function cargarComputadoras() {
    try {
        const response = await fetch(API_URL);
        const computadoras = await response.json();
        
        const tbody = document.getElementById('listaComputadoras');
        tbody.innerHTML = '';
        
        if (computadoras.length === 0) {
            tbody.innerHTML = '<tr><td colspan="9" class="text-center">No hay computadoras registradas</td></tr>';
            return;
        }
        
        computadoras.forEach(computadora => {
            const tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${computadora.id}</td>
                <td>${computadora.marca}</td>
                <td>${computadora.modelo}</td>
                <td>${computadora.procesador}</td>
                <td>${computadora.memoriaRam}</td>
                <td>${computadora.almacenamiento}GB ${computadora.tipoAlmacenamiento}</td>
                <td>$${computadora.precio.toFixed(2)}</td>
                <td>${computadora.stock}</td>
                <td>
                    <button class="btn btn-sm btn-secondary" onclick="editarComputadora(${computadora.id})">Editar</button>
                    <button class="btn btn-sm btn-danger" onclick="eliminarComputadora(${computadora.id})">Eliminar</button>
                </td>
            `;
            tbody.appendChild(tr);
        });
    } catch (error) {
        console.error('Error al cargar computadoras:', error);
        mostrarMensaje('Error al cargar las computadoras', 'error');
    }
}

// Mostrar formulario para nueva computadora
function mostrarFormulario() {
    document.getElementById('tituloFormulario').textContent = 'Nueva Computadora';
    document.getElementById('formularioComputadora').reset();
    document.getElementById('computadoraId').value = '';
    document.getElementById('modalFormulario').style.display = 'block';
}

// Cerrar formulario
function cerrarFormulario() {
    document.getElementById('modalFormulario').style.display = 'none';
}

// Editar computadora
async function editarComputadora(id) {
    try {
        const response = await fetch(`${API_URL}/${id}`);
        const computadora = await response.json();
        
        document.getElementById('tituloFormulario').textContent = 'Editar Computadora';
        document.getElementById('computadoraId').value = computadora.id;
        document.getElementById('marca').value = computadora.marca;
        document.getElementById('modelo').value = computadora.modelo;
        document.getElementById('procesador').value = computadora.procesador;
        document.getElementById('memoriaRam').value = computadora.memoriaRam;
        document.getElementById('almacenamiento').value = computadora.almacenamiento;
        document.getElementById('tipoAlmacenamiento').value = computadora.tipoAlmacenamiento;
        document.getElementById('precio').value = computadora.precio;
        document.getElementById('stock').value = computadora.stock;
        
        document.getElementById('modalFormulario').style.display = 'block';
    } catch (error) {
        console.error('Error al cargar computadora:', error);
        mostrarMensaje('Error al cargar la computadora', 'error');
    }
}

// Guardar computadora (crear o actualizar)
async function guardarComputadora(event) {
    event.preventDefault();
    
    const id = document.getElementById('computadoraId').value;
    const computadora = {
        marca: document.getElementById('marca').value,
        modelo: document.getElementById('modelo').value,
        procesador: document.getElementById('procesador').value,
        memoriaRam: parseInt(document.getElementById('memoriaRam').value),
        almacenamiento: parseInt(document.getElementById('almacenamiento').value),
        tipoAlmacenamiento: document.getElementById('tipoAlmacenamiento').value,
        precio: parseFloat(document.getElementById('precio').value),
        stock: parseInt(document.getElementById('stock').value)
    };
    
    try {
        let response;
        if (id) {
            // Actualizar
            response = await fetch(`${API_URL}/${id}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(computadora)
            });
        } else {
            // Crear
            response = await fetch(API_URL, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(computadora)
            });
        }
        
        if (response.ok) {
            cerrarFormulario();
            cargarComputadoras();
            mostrarMensaje('Computadora guardada exitosamente', 'success');
        } else {
            throw new Error('Error al guardar');
        }
    } catch (error) {
        console.error('Error al guardar computadora:', error);
        mostrarMensaje('Error al guardar la computadora', 'error');
    }
}

// Eliminar computadora
async function eliminarComputadora(id) {
    if (!confirm('¿Está seguro de eliminar esta computadora?')) {
        return;
    }
    
    try {
        const response = await fetch(`${API_URL}/${id}`, {
            method: 'DELETE'
        });
        
        if (response.ok) {
            cargarComputadoras();
            mostrarMensaje('Computadora eliminada exitosamente', 'success');
        } else {
            throw new Error('Error al eliminar');
        }
    } catch (error) {
        console.error('Error al eliminar computadora:', error);
        mostrarMensaje('Error al eliminar la computadora', 'error');
    }
}

// Mostrar mensaje
function mostrarMensaje(texto, tipo) {
    const mensaje = document.getElementById('mensaje');
    mensaje.textContent = texto;
    mensaje.className = `alert alert-${tipo}`;
    mensaje.style.display = 'block';
    
    setTimeout(() => {
        mensaje.style.display = 'none';
    }, 3000);
}