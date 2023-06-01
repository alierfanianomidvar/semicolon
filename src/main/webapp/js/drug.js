import drugUrls from './urls/drugUrls.js';
const endpoint = drugUrls.GET_ALL;
const router = new Router();

// Get all drugs data
let data;
/*router.createFetch(endpoint)
    .then(response => {
        data = response;
        populateTable(data);
    })
    .catch(error => console.error(error));*/

$.ajax(drugUrls.GET_ALL)
    .done(res => {
        data = res.data;
        populateTable(data)
    })
// Populate table with data
function populateTable(data) {
    const tbody = document.querySelector('tbody');
    tbody.innerHTML = '';

    if (!data.length){
        const tr = document.createElement('tr');
        const td = document.createElement('td');
        td.textContent = "No Drug Found!";
        td.className = "text-center";
        td.colSpan = 5;
        tr.appendChild(td);
        tbody.appendChild(tr);
    }
    data.forEach((drug) => {
        const tr = document.createElement('tr');

        const nameTd = document.createElement('td');
        nameTd.textContent = drug.name;
        tr.appendChild(nameTd);

        const ageGroupTd = document.createElement('td');
        ageGroupTd.textContent = drug.ageGroup;
        tr.appendChild(ageGroupTd);

        const sensitivityTd = document.createElement('td');
        sensitivityTd.textContent = drug.sensitive ? 'Sensitive' : 'Not Sensitive';
        tr.appendChild(sensitivityTd);

        const priceTd = document.createElement('td');
        priceTd.textContent = `$${drug.price}`;
        tr.appendChild(priceTd);

        const limitationTd = document.createElement('td');
        limitationTd.textContent = drug.limitation;
        tr.appendChild(limitationTd);

        tbody.appendChild(tr);
    });
}

// Filter table based on selected options
const filterForm = document.getElementById('filterForm');
filterForm.addEventListener('submit', (event) => {
    event.preventDefault();
    const nameFilter = document.getElementById('filterName').value.toLowerCase();
    const sensitivityFilter = document.getElementById('filterSensitivity').value;
    const ageFilter = document.getElementById('filterAge').value;
    const priceFilter = document.getElementById('filterPrice').value;

    let filteredData = data.filter((drug) => {
        let pass = true;

        if (nameFilter !== '' && !drug.name.toLowerCase().includes(nameFilter)) {
            pass = false;
        }
        if (ageFilter !== '' && drug.ageGroup !== ageFilter) {
            pass = false;
        }
        if (sensitivityFilter !== '' && drug.sensitive !== (sensitivityFilter == 'true')) {
            pass = false;
        }

        return pass;
    });

    if (priceFilter === 'ASC') {
        filteredData.sort((a, b) => a.price - b.price);
    } else if (priceFilter === 'DESC') {
        filteredData.sort((a, b) => b.price - a.price);
    }

    populateTable(filteredData);
});