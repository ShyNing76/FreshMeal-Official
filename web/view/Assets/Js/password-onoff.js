const inputs = document.querySelectorAll('.input-edit');
const eyeOpens = document.querySelectorAll('.eye-open');
const eyeCloses = document.querySelectorAll('.eye-close');

eyeOpens.forEach((eyeOpen, index) => {
    eyeOpen.addEventListener('click', () => {
        eyeOpen.classList.add('hidden-icon');
        eyeCloses[index].classList.remove('hidden-icon');
        inputs[index].type = 'text';
    });
});

eyeCloses.forEach((eyeClose, index) => {
    eyeClose.addEventListener('click', () => {
        eyeClose.classList.add('hidden-icon');
        eyeOpens[index].classList.remove('hidden-icon');
        inputs[index].type = 'password';
    });
});
