import Button from '@material-ui/core/Button'
import List from '@material-ui/core/List'
import ListItem from '@material-ui/core/ListItem'
import { React, Component } from 'react'
import TextField from '@material-ui/core/TextField'
import { withStyles } from '@material-ui/core'

const styles = {
  submitButton: {
    display: 'flex',
    justifyContent: 'flex-end',
    marginBottom: '20px',
    marginRight: '20px',
  },

  textFieldWidth: {
    marginTop: '20px',
    width: '26ch',
  },
}

class InputsList extends Component {
  render() {
    const {
      classes,
      currentTextNum,
      currentCellNum,
      currentHomeNum,
      handlePhoneChange,
      handleSubmit,
      handleKeyDown,
    } = this.props

    return (
      <div className='InputsList'>
        <List aria-label='phone number form'>
          <ListItem>
            <TextField
              className={classes.textFieldWidth}
              variant='outlined'
              label='For Text Alerts:'
              type='tel'
              id='textNum'
              name='textNum'
              color='secondary'
              maxLength={10}
              value={currentTextNum}
              onChange={(event) => handlePhoneChange('newTextNum', event)}
              onKeyDown={handleKeyDown}
            />
          </ListItem>
          <ListItem>
            <TextField
              className={classes.textFieldWidth}
              variant='outlined'
              label='For Voice Alerts (Cell):'
              type='tel'
              id='cellNum'
              name='cellVoiceNum'
              color='secondary'
              maxLength={10}
              value={currentCellNum}
              onChange={(event) => handlePhoneChange('newCellNum', event)}
              onKeyDown={handleKeyDown}
            />
          </ListItem>
          <ListItem>
            <TextField
              className={classes.textFieldWidth}
              variant='outlined'
              label='For Voice Alerts (Home):'
              type='tel'
              id='homeNum'
              name='homeNum'
              color='secondary'
              maxLength={10}
              value={currentHomeNum}
              onChange={(event) => handlePhoneChange('newHomeNum', event)}
              onKeyDown={handleKeyDown}
            />
          </ListItem>
        </List>
        <div className={classes.submitButton}>
          <Button type='submit' onClick={handleSubmit} variant='contained' color='secondary'>
            Submit
          </Button>
        </div>
      </div>
    )
  }
}

export default withStyles(styles)(InputsList)
