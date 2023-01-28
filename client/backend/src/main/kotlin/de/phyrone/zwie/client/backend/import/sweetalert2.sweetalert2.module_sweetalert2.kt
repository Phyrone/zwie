@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS")
package sweetalert2

import de.phyrone.zwie.client.backend.import.Readonly
import de.phyrone.zwie.client.backend.import.Record
import kotlin.js.*
import org.khronos.webgl.*
import org.w3c.dom.*
import org.w3c.dom.events.*
import org.w3c.dom.parsing.*
import org.w3c.dom.svg.*
import org.w3c.dom.url.*
import org.w3c.fetch.*
import org.w3c.files.*
import org.w3c.notifications.*
import org.w3c.performance.*
import org.w3c.workers.*
import org.w3c.xhr.*
import sweetalert2.Swal.DismissReason


external interface SweetAlertHideShowClass {
    var backdrop: dynamic /* String? | Array<String>? */
        get() = definedExternally
        set(value) = definedExternally
    var icon: dynamic /* String? | Array<String>? */
        get() = definedExternally
        set(value) = definedExternally
    var popup: dynamic /* String? | Array<String>? */
        get() = definedExternally
        set(value) = definedExternally
}

typealias Awaited<T> = Any

external interface `T$3`<T> {
    var toPromise: () -> T
}

typealias SweetAlertHideClass = SweetAlertHideShowClass

typealias SweetAlertShowClass = Readonly<SweetAlertHideShowClass>

external interface SweetAlertCustomClass {
    var container: dynamic /* String? | Array<String>? */
        get() = definedExternally
        set(value) = definedExternally
    var popup: dynamic /* String? | Array<String>? */
        get() = definedExternally
        set(value) = definedExternally
    var title: dynamic /* String? | Array<String>? */
        get() = definedExternally
        set(value) = definedExternally
    var closeButton: dynamic /* String? | Array<String>? */
        get() = definedExternally
        set(value) = definedExternally
    var icon: dynamic /* String? | Array<String>? */
        get() = definedExternally
        set(value) = definedExternally
    var image: dynamic /* String? | Array<String>? */
        get() = definedExternally
        set(value) = definedExternally
    var htmlContainer: dynamic /* String? | Array<String>? */
        get() = definedExternally
        set(value) = definedExternally
    var input: dynamic /* String? | Array<String>? */
        get() = definedExternally
        set(value) = definedExternally
    var inputLabel: dynamic /* String? | Array<String>? */
        get() = definedExternally
        set(value) = definedExternally
    var validationMessage: dynamic /* String? | Array<String>? */
        get() = definedExternally
        set(value) = definedExternally
    var actions: dynamic /* String? | Array<String>? */
        get() = definedExternally
        set(value) = definedExternally
    var confirmButton: dynamic /* String? | Array<String>? */
        get() = definedExternally
        set(value) = definedExternally
    var denyButton: dynamic /* String? | Array<String>? */
        get() = definedExternally
        set(value) = definedExternally
    var cancelButton: dynamic /* String? | Array<String>? */
        get() = definedExternally
        set(value) = definedExternally
    var loader: dynamic /* String? | Array<String>? */
        get() = definedExternally
        set(value) = definedExternally
    var footer: dynamic /* String? | Array<String>? */
        get() = definedExternally
        set(value) = definedExternally
    var timerProgressBar: dynamic /* String? | Array<String>? */
        get() = definedExternally
        set(value) = definedExternally
}

external interface SweetAlertResult<T> {
    var isConfirmed: Boolean
    var isDenied: Boolean
    var isDismissed: Boolean
    var value: T?
        get() = definedExternally
        set(value) = definedExternally
    var dismiss: DismissReason?
        get() = definedExternally
        set(value) = definedExternally
}

external interface SweetAlertResultPartial<T> {
    var isConfirmed: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var isDenied: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var isDismissed: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var value: T?
        get() = definedExternally
        set(value) = definedExternally
    var dismiss: DismissReason?
        get() = definedExternally
        set(value) = definedExternally
}

external interface SweetAlertResult__0 : SweetAlertResult<Any>

external interface SweetAlertResult__0Partial : SweetAlertResultPartial<Any>

external interface SweetAlertOptions<PreConfirmResult, PreConfirmCallbackValue> {
    var title: dynamic /* String? | HTMLElement? | JQuery? */
        get() = definedExternally
        set(value) = definedExternally
    var titleText: String?
        get() = definedExternally
        set(value) = definedExternally
    var text: String?
        get() = definedExternally
        set(value) = definedExternally
    var html: dynamic /* String? | HTMLElement? | JQuery? */
        get() = definedExternally
        set(value) = definedExternally
    var icon: String? /* "success" | "error" | "warning" | "info" | "question" */
        get() = definedExternally
        set(value) = definedExternally
    var iconColor: String?
        get() = definedExternally
        set(value) = definedExternally
    var iconHtml: String?
        get() = definedExternally
        set(value) = definedExternally
    var footer: dynamic /* String? | HTMLElement? | JQuery? */
        get() = definedExternally
        set(value) = definedExternally
    var template: dynamic /* String? | HTMLTemplateElement? */
        get() = definedExternally
        set(value) = definedExternally
    var backdrop: dynamic /* Boolean? | String? */
        get() = definedExternally
        set(value) = definedExternally
    var toast: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var target: dynamic /* String? | HTMLElement? */
        get() = definedExternally
        set(value) = definedExternally
    var input: String? /* "text" | "email" | "password" | "number" | "tel" | "range" | "textarea" | "select" | "radio" | "checkbox" | "file" | "url" */
        get() = definedExternally
        set(value) = definedExternally
    var width: dynamic /* Number? | String? */
        get() = definedExternally
        set(value) = definedExternally
    var padding: dynamic /* Number? | String? */
        get() = definedExternally
        set(value) = definedExternally
    var color: String?
        get() = definedExternally
        set(value) = definedExternally
    var background: String?
        get() = definedExternally
        set(value) = definedExternally
    var position: String? /* "top" | "top-start" | "top-end" | "top-left" | "top-right" | "center" | "center-start" | "center-end" | "center-left" | "center-right" | "bottom" | "bottom-start" | "bottom-end" | "bottom-left" | "bottom-right" */
        get() = definedExternally
        set(value) = definedExternally
    var grow: dynamic /* "row" | "column" | "fullscreen" | Boolean? */
        get() = definedExternally
        set(value) = definedExternally
    var showClass: SweetAlertShowClass?
        get() = definedExternally
        set(value) = definedExternally
    var hideClass: SweetAlertHideClass?
        get() = definedExternally
        set(value) = definedExternally
    var customClass: dynamic /* SweetAlertCustomClass? | String? */
        get() = definedExternally
        set(value) = definedExternally
    var timer: Number?
        get() = definedExternally
        set(value) = definedExternally
    var timerProgressBar: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var heightAuto: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var allowOutsideClick: dynamic /* Boolean? | (() -> Boolean)? */
        get() = definedExternally
        set(value) = definedExternally
    var allowEscapeKey: dynamic /* Boolean? | (() -> Boolean)? */
        get() = definedExternally
        set(value) = definedExternally
    var allowEnterKey: dynamic /* Boolean? | (() -> Boolean)? */
        get() = definedExternally
        set(value) = definedExternally
    var stopKeydownPropagation: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var keydownListenerCapture: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var showConfirmButton: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var showDenyButton: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var showCancelButton: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var confirmButtonText: String?
        get() = definedExternally
        set(value) = definedExternally
    var denyButtonText: String?
        get() = definedExternally
        set(value) = definedExternally
    var cancelButtonText: String?
        get() = definedExternally
        set(value) = definedExternally
    var confirmButtonColor: String?
        get() = definedExternally
        set(value) = definedExternally
    var denyButtonColor: String?
        get() = definedExternally
        set(value) = definedExternally
    var cancelButtonColor: String?
        get() = definedExternally
        set(value) = definedExternally
    var confirmButtonAriaLabel: String?
        get() = definedExternally
        set(value) = definedExternally
    var denyButtonAriaLabel: String?
        get() = definedExternally
        set(value) = definedExternally
    var cancelButtonAriaLabel: String?
        get() = definedExternally
        set(value) = definedExternally
    var buttonsStyling: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var reverseButtons: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var focusConfirm: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var focusDeny: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var focusCancel: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var returnFocus: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var showCloseButton: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var closeButtonHtml: String?
        get() = definedExternally
        set(value) = definedExternally
    var closeButtonAriaLabel: String?
        get() = definedExternally
        set(value) = definedExternally
    var loaderHtml: String?
        get() = definedExternally
        set(value) = definedExternally
    var showLoaderOnConfirm: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var showLoaderOnDeny: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    val preConfirm: ((inputValue: PreConfirmCallbackValue) -> PreConfirmResult)?
    val preDeny: ((value: Any) -> dynamic)?
    var imageUrl: String?
        get() = definedExternally
        set(value) = definedExternally
    var imageWidth: dynamic /* Number? | String? */
        get() = definedExternally
        set(value) = definedExternally
    var imageHeight: dynamic /* Number? | String? */
        get() = definedExternally
        set(value) = definedExternally
    var imageAlt: String?
        get() = definedExternally
        set(value) = definedExternally
    var inputLabel: String?
        get() = definedExternally
        set(value) = definedExternally
    var inputPlaceholder: String?
        get() = definedExternally
        set(value) = definedExternally
    var inputValue: dynamic /* String? | Number? | Boolean? | Promise<dynamic /* String | Number | Boolean */>? | `T$3`<dynamic /* String | Number | Boolean */>? */
        get() = definedExternally
        set(value) = definedExternally
    var inputOptions: dynamic /* ReadonlyMap<String, String>? | Record<String, Any>? | Promise<dynamic /* ReadonlyMap<String, String> | Record<String, Any> */>? | `T$3`<dynamic /* ReadonlyMap<String, String> | Record<String, Any> */>? */
        get() = definedExternally
        set(value) = definedExternally
    var inputAutoFocus: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var inputAutoTrim: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var inputAttributes: Record<String, String>?
        get() = definedExternally
        set(value) = definedExternally
    val inputValidator: ((inputValue: String) -> dynamic)?
    var returnInputValueOnDeny: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var validationMessage: String?
        get() = definedExternally
        set(value) = definedExternally
    var progressSteps: Array<String>?
        get() = definedExternally
        set(value) = definedExternally
    var currentProgressStep: Number?
        get() = definedExternally
        set(value) = definedExternally
    var progressStepsDistance: dynamic /* Number? | String? */
        get() = definedExternally
        set(value) = definedExternally
    val willOpen: ((popup: HTMLElement) -> Unit)?
    val didOpen: ((popup: HTMLElement) -> Unit)?
    val didRender: ((popup: HTMLElement) -> Unit)?
    val willClose: ((popup: HTMLElement) -> Unit)?
    val didClose: (() -> Unit)?
    val didDestroy: (() -> Unit)?
    var scrollbarPadding: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface SweetAlertOptions__1<PreConfirmResult> : SweetAlertOptions<PreConfirmResult, Any>

external interface SweetAlertOptions__0 : SweetAlertOptions<Any, Any>