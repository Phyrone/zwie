# Concepts
## Users
  - Does not differentiate between humans bots
  - users always have a uid
    - the uid conatins 2 parts
      - the first part is the identifier
      - the second part is the provider
      - the second part is optional (if empty the user is a stranger)
  - users hava metadata which can be different depending on the context
    - username
    - profile picture
    - status
    - etc
  - stragner status
    - does not belong to any server
    - 
## Channels
  - channels have features like
    - messages
    - talk
    - etc
  - communication via
    - event streams
    - state streams
  - features can be limited by permissions
  - channels can have metadata
    - name
    - description
    - picture
    - etc 
## Space
 - have a channellayout
   - structures channels in a tree
   - can be different for different users
 - have a user list
   - peeking users
     - disabear from user list after disconnect
   - joined users
     - stay in user list as offline after disconnect 
## Server
  - on or more instances users can contact
  - servers have 0 ore more spaces
  - servers have 0 or more users
  - users do not need to be present on a space vice versa
   - -> servers can act as identity server or only space server
   - users on servers can create channels without spaces
     - stuctured in a named list (iterable key value)
     - user users can join them

## Features
- are an abstraction for a certain type of data




# Concept Server API
[url]: http url the server

[space]: pace id

[url]/.well-known/zwie
[url]/.zapi/server

