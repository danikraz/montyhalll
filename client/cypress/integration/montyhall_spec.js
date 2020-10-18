describe("Get simulation result from backend", function () {
    it("Sends an input to backend", function () {
        cy.visit("http://localhost:3000/")
        cy.get("[data-cy=simulations-input]").type("10")

        cy.get("[data-cy=monty-form]").submit()
    })

    it("Gets a reponse from backend", function () {
        cy.get("[data-cy=simulations-result]")
            .should('contain', 'The player won')
    })
});

describe("Get an error from backend", function () {
    it("Sends an invalid input to backend", function () {
        cy.visit("http://localhost:3000/")
        cy.get("[data-cy=simulations-input]").type("100000000000")

        cy.get("[data-cy=monty-form]").submit()
    })

    it("Show error toast", function () {
        cy.get('.Toastify__toast-body').should('contain', 'Request failed with status code 400');
    })
});

