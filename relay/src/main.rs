use log::info;
use rsocket_rust::prelude::RSocketFactory;
use rsocket_rust_transport_websocket::WebsocketServerTransport;
use simple_logger::SimpleLogger;

#[tokio::main]
async fn main() -> Result<(), Box<dyn std::error::Error>> {
    SimpleLogger::new()
        .with_colors(true)
        .with_threads(true)
        .init()
        .unwrap();
    info!("Starting...");

    RSocketFactory::receive()
        .transport(WebsocketServerTransport::from("0.0.0.0:3344"))
        .acceptor(Box::new(|setup, _sending_socket| {
            info!("incoming socket: setup={:?}", setup);
            todo!()
        }))
        .serve()
        .await
        .unwrap();

    Ok(())
}
