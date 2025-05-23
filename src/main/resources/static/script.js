const userCardTemplate = document.querySelector("[data-user-template]")
const userCardContainer = document.querySelector("[data-user-cards-container]")
const searchInput = document.querySelector("[data-search]")

let users = []

searchInput.addEventListener("input", e => {
    const value = e.target.value.toLowerCase().trim();

    users.forEach(user => {
        const matches = user.username.toLowerCase().includes(value) || user.login.toLowerCase().includes(value);
        const isVisible = value !== "" && matches;
        user.element.classList.toggle("hide", !isVisible);
    });
});

fetch("http://localhost:8080/users")
    .then(res => res.json())
    .then(data => {
        users = data.map(user => {
            const card = userCardTemplate.content.cloneNode(true).children[0]
            const header = card.querySelector("[data-header]")
            const body = card.querySelector("[data-body]")
            header.textContent = user.username
            body.textContent = user.login

            card.classList.add("hide")

            card.addEventListener('click', () => {
                window.location.href = `http://localhost:8080/users/profile/${user.login}`;
            });

            userCardContainer.append(card)
            return { username: user.username, login: user.login, element: card }
        })
    })