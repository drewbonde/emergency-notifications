import { Button } from '@material-ui/core'
import { Dialog, DialogActions, DialogContent, DialogTitle } from '@material-ui/core'
import Error from '@material-ui/icons/Error'
import { Typography } from '@material-ui/core'
import { withStyles } from '@material-ui/core'
import { React, Component } from 'react'

const styles = {
  close_button: {
    display: 'flex',
    justifyContent: 'flex-end',
    paddingBottom: 10,
    paddingRight: 10
  },   
  dialog_title: {
    display: 'flex',
    alignItems: 'center',
    fontSize: '1.4rem'
  },
  error_display: {
    borderLeft: '15px solid #D32F2F',
  },
  error_icon: {
    color: '#D32F2F',
    fontSize: 32,
    marginRight: '10px'
  }, 
}

class ErrorDialog extends Component {
  render() {
    const {
      classes,
      error,
      handleErrorClose
    } = this.props

    return (
      <div className='ErrorDialog'>
        <Dialog open={error} onClose={handleErrorClose} classes={{paper: classes.error_display}}>
          <DialogTitle className={classes.dialog_title}>
            <Typography className={classes.dialog_title}>
              <Error className={classes.error_icon} /> Error Updating Numbers
            </Typography>
          </DialogTitle>
          <DialogContent>
            <Typography>
              Updating emergency contact numbers resulted in an error.
            </Typography>
          </DialogContent>
          <DialogActions>
            <div className={classes.close_button}>
              <Button onClick={handleErrorClose} color='secondary'>
                Close
              </Button>
            </div>
          </DialogActions>
        </Dialog>
      </div>
    )
  }
}

export default withStyles(styles)(ErrorDialog)