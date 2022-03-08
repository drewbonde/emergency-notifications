import classnames from 'classnames'
import ExpandMoreIcon from '@material-ui/icons/ExpandMore'
import IconButton from '@material-ui/core/IconButton'
import React, { Component } from 'react'
import Tooltip from '@material-ui/core/Tooltip'
import Typography from '@material-ui/core/Typography'
import { withStyles } from '@material-ui/core'

const styles = {
  dropDownIcon: {
    alignSelf: 'center',
    fontSize: '50px',
    padding: 10,
  },

  expandOpen: {
    transform: 'rotate(180deg)',
  },

  tooltip: {
    fontSize: '.8rem',
  },
}

class CollapseButton extends Component {
  state = {
    expanded: false,
  }

  render() {
    const { classes, expanded, handleExpand } = this.props

    const displayLabel = !expanded
      ? 'Click to Show Emergency Contact Numbers'
      : 'Click to Close Emergency Contact Numbers'

    return (
      <div className='CollapseButton'>
        <div className={classes.dropDownIcon}>
          <Tooltip
            className={classes.tooltip}
            title={
              <React.Fragment>
                <Typography className={classes.tooltip} color='inherit'>
                  {displayLabel}
                </Typography>
              </React.Fragment>
            }
          >
            <IconButton
              aria-label={displayLabel}
              className={classnames(classes.expand, {
                [classes.expandOpen]: expanded,
              })}
              onClick={handleExpand}
            >
              <ExpandMoreIcon />
            </IconButton>
          </Tooltip>
        </div>
      </div>
    )
  }
}

export default withStyles(styles)(CollapseButton)
