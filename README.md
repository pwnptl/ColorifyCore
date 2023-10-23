### Colorify

## 1. About Game:

### Running Server :
`ssh -R 80:localhost:8080 serveo.net`
---
## 2. APIs
### 0. Socket Connect url
* `ws://192.168.35.10:8080/myHandler` // todo: dev to update into a hosted link :|
### 1.Player APis
#### 1. create a new Player
```json
{
  "messageType": "CREATE_PLAYER",
  "messageData": {
    "name": "String"
  }
}
```

```json
{
  "messageType": "PLAYER_CREATED",
  "messageData": {
    "playerId": "String"
  }
}
```
#### 2. get a player
```json
{
  "messageType": "GET_PLAYER_DATA",
  "messageData": {
    "playerId": "<id>"
  }
}
```

```json
{
  "messageType": "PLAYER_DATA",
  "messageData": {
    "playerId": "String",
    "name": "String",
    "type": "PlayerType" 
  }
}
```
#### 3. register a player session

### 2. Game APIs
#### 1. create a game

#### 2. join a game

#### 3. make a move in game

### 3. Server pushes


---
## 4. Data Types:
1. PlayerType : Enum \
  `human` | `bot` | `unrecognised`
2. 


---



         heehee      /aa \_
                   __\-  / )                 .-.
         .-.      (__/    /        haha    _/oo \
       _/ ..\       /     \               ( \v  /__
      ( \  u/__    /       \__             \/   ___)
       \    \__)   \_.-._._   )  .-.       /     \
       /     \             `-`  / ee\_    /       \_
    __/       \               __\  o/ )   \_.-.__   )
    (   _._.-._/     hoho     (___   \/           '-'
     '-'                        /     \
                              _/       \    teehee
                             (   __.-._/