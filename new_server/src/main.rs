use crate::cli::StartArguments;
use actix_web::{App, HttpServer};
use clap::Parser;

use log::{debug, error, info, LevelFilter};
use simple_logger::SimpleLogger;

mod cli;
mod user;
mod channel;

#[tokio::main]
async fn main() {
    let command_line: StartArguments = StartArguments::parse();

    SimpleLogger::new()
        .with_colors(true)
        .with_threads(true)
        .with_local_timestamps()
        .with_level(command_line.level.to_level_filter())
        .init()
        .expect("Failed to initialize logger");

    debug!("command_line: {:?}", command_line);
    info!("Starting...");



    info!(
        "Starting server on {}:{}",
        command_line.host, command_line.port
    );
    let result = HttpServer::new(|| App::new()

    )
        .bind(("0.0.0.0", command_line.port))
        .unwrap()
        .run()
        .await;

    /*
    let mut res = rt
        .execute_script(
            "script",
            r#"
            export const a = "";
            "#,
        )
        .expect("Failed to execute script");

    println!("{:?}", res);
    drop(res);
     */

    let mut a = Vec::new();
    a.push("hi");
}
