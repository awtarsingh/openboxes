import React from 'react';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import Translate from '../../utils/Translate';
import GlobalSearch from '../GlobalSearch';
import LocationChooser from '../location/LocationChooser';
import apiClient from '../../utils/apiClient';

/** Logs out impersonated user and redirects to dashboard */
function logoutImpersonatedUser() {
  const url = '/openboxes/api/logout';

  apiClient.post(url)
    .then(() => {
      window.location = '/openboxes/dashboard/index';
    });
}

const Header = ({
  username, isImpersonated, currentLocationId, logoLabel,
}) => (
  <div className="w-100">
    {isImpersonated ?
      <div className="d-flex notice">
        <div className="ml-1"><Translate id="react.default.impersonate.label" defaultMessage="You are impersonating user" /></div>
        <div className="ml-1"><b>{username}</b></div>
        <div className="ml-1">
          <a
            href="#"
            onClick={() => logoutImpersonatedUser()}
          >
            <Translate id="react.default.logout.label" defaultMessage="Logout" />
          </a>
        </div>
      </div> : null}
    <div className="d-flex align-items-center justify-content-between flex-wrap">
      <div className="logo-header">
        <a
          href="/openboxes"
          className="navbar-brand brand-name"
        >
          <img alt="Openboxes" src={`/openboxes/location/viewLogo/${currentLocationId}`} onError={(e) => { e.target.onerror = null; e.target.src = 'https://openboxes.com/img/logo_30.png'; }} />
        </a>
        { logoLabel.trim() !== '' ? <span>{logoLabel} </span> : null }
      </div>
      <div className="d-flex flex-wrap">
        <GlobalSearch />
        <LocationChooser />
      </div>
    </div>
  </div>
);

const mapStateToProps = state => ({
  username: state.session.user.username,
  isImpersonated: state.session.isImpersonated,
  currentLocationId: state.session.currentLocation.id,
  logoLabel: state.session.logoLabel,
});

export default connect(mapStateToProps)(Header);

Header.propTypes = {
  /** Active user's username */
  username: PropTypes.string.isRequired,
  /** Indicator if active user is impersonated */
  isImpersonated: PropTypes.bool.isRequired,
  /** Id of the current location */
  currentLocationId: PropTypes.string.isRequired,
  /** Id of the current location */
  logoLabel: PropTypes.string.isRequired,
};
