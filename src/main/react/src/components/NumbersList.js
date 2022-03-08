import Button from '@material-ui/core/Button'
import List from '@material-ui/core/List'
import ListItem from '@material-ui/core/ListItem'
import ListItemText from '@material-ui/core/ListItemText'
import { React, Component } from 'react'
import { withStyles } from '@material-ui/core'

const styles = {
  submitButton: {
    display: 'flex',
    justifyContent: 'flex-end',
    marginBottom: '20px',
    marginRight: '20px',
  },
}

class NumbersList extends Component {
  render() {
    const { classes, currentTextNum, currentCellNum, currentHomeNum, handleEdit } = this.props

    return (
      <div className='NumbersList'>
        <List aria-label='phone-numbers'>
          <ListItem>
            <ListItemText primary={`For Text Alerts: ${currentTextNum || ''}`} />
          </ListItem>
          <ListItem>
            <ListItemText primary={`For Voice Alerts (Cell): ${currentCellNum || ''}`} />
          </ListItem>
          <ListItem>
            <ListItemText primary={`For Voice Alerts (Home): ${currentHomeNum || ''}`} />
          </ListItem>
        </List>
        <div className={classes.submitButton}>
          <Button onClick={handleEdit} variant='contained' color='secondary'>
            Update
          </Button>
        </div>
      </div>
    )
  }
}

export default withStyles(styles)(NumbersList)
