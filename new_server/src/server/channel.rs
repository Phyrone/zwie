use serde::{Deserialize, Serialize};
use std::rc::Rc;
use surrealdb::sql::Uuid;

#[derive(Clone, Debug, Serialize, Deserialize)]
struct ChannelLayout {
    channels: Vec<Channel>,
}

#[derive(Clone, Debug, Serialize, Deserialize)]
struct Channel {
    uuid: Uuid,
    name: String,
}
