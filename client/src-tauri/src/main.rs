#![cfg_attr(
    all(not(debug_assertions), target_os = "windows"),
    windows_subsystem = "windows"
)]

//#[cfg(all(target_os = "macos", not(debug_assertions)))]
//embed_plist::embed_info_plist!("../Info.plist");

//#[cfg(not(debug_assertions))]
//#[cfg(target_os = "macos")]
//embed_plist::embed_info_plist!("../Info.plist");

//#[cfg(debug_assertions)]
//#[cfg(target_os = "macos")]
//embed_plist::embed_info_plist!("../Info.plist");

fn main() {
    tauri::Builder::default()
        .plugin(tauri_plugin_window_state::Builder::default().build())
        .run(tauri::generate_context!())
        .expect("error while running tauri application")
}