import React from 'react'
import ContactsTableRow from './ContactsTableRow'

class ContactsTable extends React.Component {
  render () {
    const contacts = this.props.contacts

    if (!contacts || contacts.length === 0) {
      return (
                <div className="alert alert-info">
                    No contacts yet.
                </div>
      )
    }

    return (
            <div className="row">
                <div className="col-md-12">
                    <table className="table table-sm table-hover table-striped">
                        <thead>
                        <tr>
                            <th>Track</th>
                            <th>By</th>
                            <th>Active</th>
                            <th>Signal</th>
                            <th>Frames</th>
                            <th>Duration</th>
                            <th>SSIDs</th>
                            <th>First Seen</th>
                            <th>Last Seen</th>
                        </tr>
                        </thead>
                        <tbody>
                        {Object.keys(contacts).map(function (key, i) {
                          return <ContactsTableRow key={contacts[key].uuid} contact={contacts[key]}/>
                        })}
                        </tbody>
                    </table>
                </div>
            </div>
    )
  }
}

export default ContactsTable
