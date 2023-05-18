import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { byteSize, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IPrecondition } from 'app/shared/model/precondition.model';
import { getEntities } from './precondition.reducer';

export const Precondition = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const preconditionList = useAppSelector(state => state.precondition.entities);
  const loading = useAppSelector(state => state.precondition.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  return (
    <div>
      <h2 id="precondition-heading" data-cy="PreconditionHeading">
        <Translate contentKey="eduApp.precondition.home.title">Preconditions</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="eduApp.precondition.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/precondition/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="eduApp.precondition.home.createLabel">Create new Precondition</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {preconditionList && preconditionList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="eduApp.precondition.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="eduApp.precondition.metadata">Metadata</Translate>
                </th>
                <th>
                  <Translate contentKey="eduApp.precondition.activity">Activity</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {preconditionList.map((precondition, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/precondition/${precondition.id}`} color="link" size="sm">
                      {precondition.id}
                    </Button>
                  </td>
                  <td>{precondition.metadata}</td>
                  <td>
                    {precondition.activity ? <Link to={`/activity/${precondition.activity.id}`}>{precondition.activity.title}</Link> : ''}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/precondition/${precondition.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/precondition/${precondition.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/precondition/${precondition.id}/delete`}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="eduApp.precondition.home.notFound">No Preconditions found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Precondition;
