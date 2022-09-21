use clap::Parser;
use clap::Subcommand;
use log::Level;

#[derive(Parser, Debug)]
#[clap(author, version, about="the corresponding server to the Zwie client", long_about = None)]
pub struct StartArguments {
    #[clap(short, long, about, default_value_t = 2343)]
    pub port: u16,

    #[clap(short, long, about,default_value="0.0.0.0")]
    pub host : String,

    #[clap(subcommand)]
    pub subcommand: Option<SubCommand>,

    #[clap(short, long, about,default_value_t = Level::Info)]
    pub level: Level,

}

#[derive(Subcommand, Debug)]
pub enum SubCommand {

}
