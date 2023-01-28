@file:JsQualifier("sweetalert2.Swal")
@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS")
package sweetalert2.Swal

import de.phyrone.zwie.client.backend.import.Pick
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
import sweetalert2.SweetAlertOptions__1
import sweetalert2.SweetAlertResult
import sweetalert2.Awaited
import sweetalert2.SweetAlertOptions__0
import sweetalert2.SweetAlertResult__0Partial

external fun <T> fire(options: SweetAlertOptions__1<T>): Promise<SweetAlertResult<Awaited<T>>>

external fun <T> fire(title: String = definedExternally, html: String = definedExternally, icon: String /* "success" | "error" | "warning" | "info" | "question" */ = definedExternally): Promise<SweetAlertResult<Awaited<T>>>

external fun <T> fire(): Promise<SweetAlertResult<Awaited<T>>>

external fun <T> fire(title: String = definedExternally): Promise<SweetAlertResult<Awaited<T>>>

external fun <T> fire(title: String = definedExternally, html: String = definedExternally): Promise<SweetAlertResult<Awaited<T>>>

external fun mixin(options: SweetAlertOptions__0): Any

external fun isVisible(): Boolean

external fun update(options: Pick<SweetAlertOptions__0, String /* "allowEscapeKey" | "allowOutsideClick" | "background" | "buttonsStyling" | "cancelButtonAriaLabel" | "cancelButtonColor" | "cancelButtonText" | "closeButtonAriaLabel" | "closeButtonHtml" | "confirmButtonAriaLabel" | "confirmButtonColor" | "confirmButtonText" | "currentProgressStep" | "customClass" | "denyButtonAriaLabel" | "denyButtonColor" | "denyButtonText" | "didClose" | "didDestroy" | "footer" | "hideClass" | "html" | "icon" | "iconColor" | "imageAlt" | "imageHeight" | "imageUrl" | "imageWidth" | "preConfirm" | "preDeny" | "progressSteps" | "reverseButtons" | "showCancelButton" | "showCloseButton" | "showConfirmButton" | "showDenyButton" | "text" | "title" | "titleText" | "willClose" */>)

external fun close(result: SweetAlertResult__0Partial = definedExternally)

external fun getContainer(): HTMLElement?

external fun getPopup(): HTMLElement?

external fun getTitle(): HTMLElement?

external fun getProgressSteps(): HTMLElement?

external fun getHtmlContainer(): HTMLElement?

external fun getImage(): HTMLElement?

external fun getCloseButton(): HTMLButtonElement?

external fun getIcon(): HTMLElement?

external fun getIconContent(): HTMLElement?

external fun getConfirmButton(): HTMLButtonElement?

external fun getDenyButton(): HTMLButtonElement?

external fun getCancelButton(): HTMLButtonElement?

external fun getActions(): HTMLElement?

external fun getFooter(): HTMLElement?

external fun getTimerProgressBar(): HTMLElement?

external fun getFocusableElements(): Array<HTMLElement>

external fun enableButtons()

external fun disableButtons()

external fun showLoading(buttonToReplace: HTMLButtonElement = definedExternally)

external fun hideLoading()

external fun isLoading(): Boolean

external fun clickConfirm()

external fun clickDeny()

external fun clickCancel()

external fun showValidationMessage(validationMessage: String)

external fun resetValidationMessage()

external fun getInput(): HTMLInputElement?

external fun disableInput()

external fun enableInput()

external fun getValidationMessage(): HTMLElement?

external fun getTimerLeft(): Number?

external fun stopTimer(): Number?

external fun resumeTimer(): Number?

external fun toggleTimer(): Number?

external fun isTimerRunning(): Boolean?

external fun increaseTimer(n: Number): Number?

external fun isValidParameter(paramName: String): Boolean

external fun isUpdatableParameter(paramName: String): Boolean

external fun <T> argsToParams(params: Any /* JsTuple<Any, Any, Any> | JsTuple<SweetAlertOptions__1<T>> */): SweetAlertOptions__1<T>

external enum class DismissReason {
    cancel,
    backdrop,
    close,
    esc,
    timer
}

external var version: String