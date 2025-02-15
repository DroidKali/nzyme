import React from 'react'

class PwnagotchiIdentityForm extends React.Component {
  constructor (props) {
    super(props)

    this.state = {
      identity: '',
      errorMessage: ''
    }

    this._handleUpdate = this._handleUpdate.bind(this)
  }

  _handleUpdate (e) {
    const identity = e.target.value.replace(/ /g, '')
    this.setState({ identity: identity, errorMessage: '' })

    if (identity.length !== 64) {
      this.setState({ errorMessage: 'Invalid Pwnagotchi identity. A valid identity is 64 characters long.' })
      this.props.setFormReady(false)
      return
    }

    const explanation = identity ? 'a Pwnagotchi with identity "' + identity + '" is recorded' : undefined

    this.props.setConfiguration({ identity: identity })

    this.props.setExplanation(explanation)
    this.props.setFormReady(true)
  }

  render () {
    return (
            <form onSubmit={(e) => e.preventDefault()}>
                <div className="form-group">
                    <label htmlFor="identity">Pwnagotchi Identity</label>
                    <input type="text" className="form-control" id="identifier" placeholder="Enter the Pwnagotchi identity"
                           value={this.state.identity} onChange={this._handleUpdate} required />

                    <span className="text-danger">{this.state.errorMessage}</span>
                </div>
            </form>
    )
  }
}

export default PwnagotchiIdentityForm
