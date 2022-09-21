use actix::{Actor, StreamHandler};
use actix_web::dev::Server;
use actix_web::{web, App, Error, HttpRequest, HttpResponse, HttpServer};
use actix_web_actors::ws;
use log::info;
use std::borrow::{Borrow, BorrowMut};

use surrealdb::{Datastore, Session};

pub mod channel;
mod user;

#[derive(Clone, Debug)]
pub struct ServerConfiguration {
    pub host: String,
    pub port: u16,
    pub domain: String,
}

pub struct ServerInstance<'server> {
    config: ServerConfiguration,
    session: Session,
    datastore: &'server Datastore,
}

impl ServerInstance<'_> {
    pub fn new<'server>(
        config: &ServerConfiguration,
        datastore: &'server Datastore,
    ) -> ServerInstance<'server> {
        let session = Session::for_db("zwie", config.host.as_str());
        ServerInstance {
            session,
            datastore,
            config: config.clone(),
        }
    }

    pub async fn run(&self) {
        let server = HttpServer::new(|| {
            App::new().route("/", web::get()).route(
                "/v1/socket",
                web::get().to(ServerInstance::handle_v1_channel),
            )
        })
        .bind((self.config.host.clone(), self.config.port))
        .unwrap() //TODO replace with proper error handling
        .run();
        info!(
            "Server started on http://{}:{}",
            self.config.host, self.config.port
        );

        server.await.unwrap();
    }

    async fn handle_v1_channel(
        req: HttpRequest,
        stream: web::Payload,
    ) -> Result<HttpResponse, Error> {
        let channel = WsChannel::new();
        let ws = ws::start(channel, &req, stream);
        
        return ws;
    }
}

struct WsChannel {}

impl WsChannel {
    fn new() -> WsChannel {
        WsChannel {}
    }
}

impl Actor for WsChannel {
    type Context = ws::WebsocketContext<Self>;
}

impl StreamHandler<Result<ws::Message, ws::ProtocolError>> for WsChannel {
    fn handle(&mut self, msg: Result<ws::Message, ws::ProtocolError>, ctx: &mut Self::Context) {
        println!("WE GOT A MESSAGE: {:?}", msg);
    }
}
