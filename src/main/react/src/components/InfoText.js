import List from '@material-ui/core/List'
import ListItem from '@material-ui/core/ListItem'
import { React, Component } from 'react'
import { withStyles } from '@material-ui/core'

const styles = {
  italicFont: {
    fontStyle: 'italic',
  },
}

class InfoText extends Component {
  render() {
    const { classes } = this.props

    return (
      <List sx={{width: '100%'}}>
        <ListItem>
          Oakland University office and residence hall phones will receive voice alerts via our
          internal voicemail alert system. The form below enables you to receive alerts to your
          personal telephone lines.
        </ListItem>
        <ListItem>
          Please Note: If an emergency notification is sent via this system, the message may not
          be delivered to all contact numbers simultaneously. The university may elect to send
          emergency notifications via e-mail and/or voicemail separately from this system.
        </ListItem>
        <ListItem>
          You may use the same phone number in more than one field.  
        </ListItem>
        <ListItem />
        <ListItem className={classes.italicFont}>
          Key in numbers with no hyphens (ex. 2481234567).
        </ListItem>
      </List>
    )
  }
}

export default withStyles(styles)(InfoText)
