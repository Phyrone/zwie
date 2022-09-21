extern crate core;

use crate::cli::StartArguments;
use actix_web::cookie::time::UtcOffset;
use actix_web::{App, HttpServer};
use clap::Parser;

use crate::server::{ServerConfiguration, ServerInstance};
use log::{debug, error, info, LevelFilter};
use simple_logger::SimpleLogger;
use surrealdb::{Datastore};

mod cli;
mod server;

#[tokio::main]
async fn main() {
    let command_line: StartArguments = StartArguments::parse();

    SimpleLogger::new()
        .with_colors(true)
        .with_threads(true)
        .with_local_timestamps()
        .with_utc_offset(UtcOffset::current_local_offset().unwrap_or(UtcOffset::UTC))
        .with_level(command_line.level.to_level_filter())
        .init()
        .expect("Failed to initialize logger");

    debug!("command_line: {:?}", command_line);
    info!("Starting...");

    info!("Starting database...");
    let store = Datastore::new("memory").await.unwrap();

    let config = ServerConfiguration {
        host: command_line.host,
        port: command_line.port,
        domain: "main".to_string(),
    };

    let server = ServerInstance::new(&config, &store);
    server.run().await;
    info!("Zzz...");
}
