// document.querySelectorAll('a[href^="#"]').forEach(anchor => {
//     anchor.addEventListener('click', function(e) {
//         e.preventDefault();
//         const targetId = this.getAttribute('href').substring(1);
//         const targetElement = document.getElementById(targetId);
//         if (targetElement) {
//             window.scrollTo({
//                 top: targetElement.offsetTop,
//                 behavior: 'smooth'
//             });
//         }
//     });
// });

// // Navbar behavior on scroll
// const navbar = document.querySelector('.navbar');
// window.addEventListener('scroll', () => {
//     if (window.scrollY > 100) {
//         navbar.classList.add('bg-light');
//     } else {
//         navbar.classList.remove('bg-light');
//     }
// });
// const showRegistration = document.getElementById('show-registration');
// const showLogin = document.getElementById('show-login');
// const loginSection = document.querySelector('.login-section');
// const registrationSection = document.querySelector('.registration-section');

// showRegistration.addEventListener('click', () => {
//     loginSection.style.display = 'none';
//     registrationSection.style.display = 'block';
// });

// showLogin.addEventListener('click', () => {
//     registrationSection.style.display = 'none';
//     loginSection.style.display = 'block';
// });