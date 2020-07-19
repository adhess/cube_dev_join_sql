# cube_dev_join_sql

donors: (id, state)

donations: (donor_id, amount)

`select state, sum(amount) from donors, donations where donations.donor_id = donors.id group by state`



const donors = [{ "id": 1, "state": "California" }, { "id": 2, "state": "New York" }, { "id": 3, "state": "California" }, { "id": 4, "state": "Florida" }];

const donations = [{ "donor_id": 1, "amount": 100 }, { "donor_id": 1, "amount": 400 }, { "donor_id": 2, "amount": 200 }, { "donor_id": 3, "amount": 300 }, { "donor_id": 4, "amount": 500 }];
