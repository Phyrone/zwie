use actix_web::{web, App, HttpResponse, HttpServer};
use log::info;
use simple_logger::SimpleLogger;



#[tokio::main]
async fn main() {
    SimpleLogger::new()
        .with_colors(true)
        .with_threads(true)
        .init()
        .unwrap();
    info!("Starting...");

    let server = HttpServer::new(|| {
        App::new()
            .route("/", web::to(||HttpResponse::Ok()))
            .route("/bob", web::to(|| HttpResponse::Ok()))
    });

    let mut started_server = server.bind(("127.0.0.1", 8080)).unwrap().run();

    started_server.await.expect("could not start server");
}
