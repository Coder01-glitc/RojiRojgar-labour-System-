const roleSelect = document.getElementById('roleSelect');

if(roleSelect){
roleSelect.addEventListener('change', function(){

const skillsField = document.getElementById('skillsField');
const companyField = document.getElementById('companyField');

skillsField.classList.add('d-none');
companyField.classList.add('d-none');

if(this.value === 'WORKER'){
skillsField.classList.remove('d-none');
}

if(this.value === 'COMPANY'){
companyField.classList.remove('d-none');
}

});
}