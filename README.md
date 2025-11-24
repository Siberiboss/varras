# Varras – Inventory MVP (Micronaut + Vue)

See on testülesanne, mis demonstreerib:
- Micronaut (Java) backend
- H2 database (PostgreSQL režiimiga)
- Vue 3 frontend (mock-login)
- REST API (Categories + Items)
- Monorepo (eraldiseisvad backend ja frontend projektid)

## Struktuur

varras/
- backend/ # Micronaut Java rakendus
- frontend/ # Vue 3 rakendus

## Käivitamine

### Backend

cd backend
./gradlew run

Jookseb:
`http://localhost:8080/api/...`

### Frontend

cd frontend
npm install
npm run dev

Jookseb:  
`http://localhost:5173`

Frontend kasutab API-sid aadressilt `/api/*`.  
(Vajadusel lisa Vite proxy.)

## Testid
Tulevad järgmises etapis.  
Backendile lisatakse Micronaut integra- ja unit-testid.

## Autor
Gene Raator