package edu.galileo.android.androidchat.login;

import de.greenrobot.event.EventBus;

/**
 * Created by ykro.
 */
public class LoginPresenterImpl implements LoginPresenter {
    LoginView loginView;
    LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractorImpl();
    }

    @Override
    public void validateLogin(String email, String password) {
        if (loginView != null) {
            loginView.hideInputs();
            loginView.showProgress();
        }
        loginInteractor.doSignIn(email, password);
    }

    @Override
    public void registerNewUser(String email, String password) {
        if (loginView != null) {
            loginView.hideInputs();
            loginView.showProgress();
        }
        loginInteractor.doSignUp(email, password);
    }

    @Override
    public void checkForAuthenticatedUser() {
        if (loginView != null) {
            loginView.hideInputs();
            loginView.showProgress();
        }
        loginInteractor.checkAlreadyAuthenticated();
    }

    @Override
    public void onPause() {
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onResume() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        loginView = null;
    }

    @Override
    public void onEvent(LoginEvent event) {
        switch (event.getEventType()) {
            case LoginEvent.onSignInError:
                onSignInError(event.getErrorMesage());
                break;
            case LoginEvent.onSignInSuccess:
                onSignInSuccess();
                break;
            case LoginEvent.onSignUpError:
                onSignUpError(event.getErrorMesage());
                break;
            case LoginEvent.onSignUpSuccess:
                onSignUpSuccess();
                break;
            case LoginEvent.onFailedToRecoverSession:
                onFailedToRecoverSession();
                break;
        }
    }

    private void onSignInSuccess() {
        if (loginView != null) {
            loginView.navigateToMainScreen();
        }
    }

    private void onSignUpSuccess() {
        if (loginView != null) {
            loginView.newUserSuccess();
        }
    }

    private void onSignInError(String error) {
        if (loginView != null) {
            loginView.loginError(error);
            loginView.hideProgress();
            loginView.showInputs();
        }
    }

    private void onSignUpError(String error) {
        if (loginView != null) {
            loginView.newUserError(error);
            loginView.hideProgress();
            loginView.showInputs();
        }
    }

    private void onFailedToRecoverSession() {
        if (loginView != null) {
            loginView.hideProgress();
            loginView.showInputs();
        }
    }
}
