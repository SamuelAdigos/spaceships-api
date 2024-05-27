const fs = require('fs');
const path = '/data/db/.initialized';

if (fs.existsSync(path)) {
  print("The database has already been initialized. Exiting...");
  quit();
}

function generateUUID() {
  let d = new Date().getTime();
  if (typeof performance !== 'undefined' && typeof performance.now
      === 'function') {
    d += performance.now();
  }
  return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
    let r = (d + Math.random() * 16) % 16 | 0;
    d = Math.floor(d / 16);
    return (c === 'x' ? r : (r & 0x3 | 0x8)).toString(16);
  });
}

db = db.getSiblingDB('spaceships');

db.createCollection('spaceships');
db.spaceships.insertMany([
  {
    _id: generateUUID(),
    _class: "com.samuel.spaceships.api.Infrastructure.Persistence.Mongo.SpaceshipData",
    name: "Enterprise",
    franchise: "Star Trek",
    maxSpeed: 8.0
  },
  {
    _id: generateUUID(),
    _class: "com.samuel.spaceships.api.Infrastructure.Persistence.Mongo.SpaceshipData",
    name: "Millennium Falcon",
    franchise: "Star Wars",
    maxSpeed: 3.0
  },
  {
    _id: generateUUID(),
    _class: "com.samuel.spaceships.api.Infrastructure.Persistence.Mongo.SpaceshipData",
    name: "Serenity",
    franchise: "Firefly",
    maxSpeed: 2.5
  },
  {
    _id: generateUUID(),
    _class: "com.samuel.spaceships.api.Infrastructure.Persistence.Mongo.SpaceshipData",
    name: "Galactica",
    franchise: "Battlestar Galactica",
    maxSpeed: 2.0
  },
  {
    _id: generateUUID(),
    _class: "com.samuel.spaceships.api.Infrastructure.Persistence.Mongo.SpaceshipData",
    name: "Discovery One",
    franchise: "2001: A Space Odyssey",
    maxSpeed: 1.5
  },
  {
    _id: generateUUID(),
    _class: "com.samuel.spaceships.api.Infrastructure.Persistence.Mongo.SpaceshipData",
    name: "Rocinante",
    franchise: "The Expanse",
    maxSpeed: 6.5
  },
  {
    _id: generateUUID(),
    _class: "com.samuel.spaceships.api.Infrastructure.Persistence.Mongo.SpaceshipData",
    name: "Andromeda Ascendant",
    franchise: "Andromeda",
    maxSpeed: 9.0
  },
  {
    _id: generateUUID(),
    _class: "com.samuel.spaceships.api.Infrastructure.Persistence.Mongo.SpaceshipData",
    name: "Heart of Gold",
    franchise: "Hitchhiker's Guide to the Galaxy",
    maxSpeed: 10.0
  },
  {
    _id: generateUUID(),
    _class: "com.samuel.spaceships.api.Infrastructure.Persistence.Mongo.SpaceshipData",
    name: "Eagle 5",
    franchise: "Spaceballs",
    maxSpeed: 1.0
  },
  {
    _id: generateUUID(),
    _class: "com.samuel.spaceships.api.Infrastructure.Persistence.Mongo.SpaceshipData",
    name: "Nostromo",
    franchise: "Alien",
    maxSpeed: 1.2
  }
]);

fs.writeFileSync(path, 'Initialization completed');
print("Initial data has been inserted. Exiting...");