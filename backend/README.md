#Prevention suicide

## Setup

### Install mongoDB

```shell
brew tap mongodb/brew
brew update
brew install mongodb-community@6.0
```

### Run mongoDB

```shell
brew services start mongodb-community@6.0
brew services list
brew services stop mongodb-community@6.0
```

### Connect mongoDB

```shell
mongosh

user PSCU01P

db.createUser(
  {
    user: "PSCU_OWN",
    pwd: "PSCU_OWN_00000",
    roles: [ { role: "userAdminAnyDatabase", db: "admin" },
             { role: "dbAdminAnyDatabase", db: "admin" },
             { role: "readWriteAnyDatabase", db: "admin" }
           ]
  }
)
```

IN CASE OF ERROR /!\\ /!\\ /!\\ ALL DATA ARE RESET
```shell
sudo rm -rf /usr/local/var/mongodb
cd /usr/local/var && mkdir mongodb
```