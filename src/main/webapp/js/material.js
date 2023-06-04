import materialUrls from './urls/materialUrls.js';
import {showErrorMessage} from "./utils.js";

let data;

const response = await fetch(materialUrls.GET_ALL.url, {
    method: materialUrls.GET_ALL.method,
    headers: {
        'Content-Type': 'application/json'
    }
})
const res = await response.json();
if (response.ok) {
    data = res.data;
    populateTable(data);
} else {
    showErrorMessage(res.msg);
    console.log('There was a problem with the fetch operation:', res.msg);
}

// Populate table with data
function populateTable(data) {
    const tbody = document.querySelector('tbody');
    tbody.innerHTML = '';

    if (!data.length){
        const tr = document.createElement('tr');
        const td = document.createElement('td');
        td.textContent = "No Material Found!";
        td.className = "text-center";
        td.colSpan = 6;
        tr.appendChild(td);
        tbody.appendChild(tr);
    }
    data.forEach((material) => {
        const tr = document.createElement('tr');

        const nameTd = document.createElement('td');
        nameTd.textContent = material.name;
        tr.appendChild(nameTd);

        const ageGroupTd = document.createElement('td');
        ageGroupTd.textContent = material.ageGroup;
        tr.appendChild(ageGroupTd);

        const supplierTd = document.createElement('td');
        supplierTd.textContent = material.supplier;
        tr.appendChild(supplierTd);

        const genderTd = document.createElement('td');
        genderTd.textContent = material.gender;
        tr.appendChild(genderTd);

        const priceTd = document.createElement('td');
        priceTd.textContent = `$${material.price}`;
        tr.appendChild(priceTd);

        const countryTd = document.createElement('td');
        countryTd.textContent = material.country;
        tr.appendChild(countryTd);

        tbody.appendChild(tr);
    });
}

// Filter table based on selected options
const filterForm = document.getElementById('filterForm');
filterForm.addEventListener('submit', (event) => {
    event.preventDefault();
    const nameFilter = document.getElementById('filterName').value.toLowerCase();
    const ageFilter = document.getElementById('filterAge').value;
    const genderFilter = document.getElementById('filterGender').value;

    let filteredData = data.filter((material) => {
        let pass = true;

        if (nameFilter !== '' && !material.name.toLowerCase().includes(nameFilter)) {
            pass = false;
        }
        if (ageFilter !== '' && material.ageGroup !== ageFilter) {
            pass = false;
        }
        if (genderFilter !== '' && material.gender !== genderFilter) {
            pass = false;
        }

        return pass;
    });

    populateTable(filteredData);
});