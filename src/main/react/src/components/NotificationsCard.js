import Card from '@material-ui/core/Card'
import CardActions from '@material-ui/core/CardActions'
import CardHeader from '@material-ui/core/CardHeader'
import CheckCircleIcon from '@material-ui/icons/CheckCircle'
import Collapse from '@material-ui/core/Collapse'
import CollapseButton from './CollapseButton'
import { getNumbers, updateNumbers } from '../api/api.js'
import InfoIcon from '@material-ui/icons/Info'
import ErrorDialog from './ErrorDialog'
import InfoText from './InfoText'
import InputsList from './InputsList'
import NumbersList from './NumbersList'
import { React, Component } from 'react'
import Typography from '@material-ui/core/Typography'
import { withStyles } from '@material-ui/core'

const styles = {
  hasNotificationsCard: {
    borderLeft: '10px solid #388E3C',
    marginTop: '15px',
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    justifyContent: 'flex-start',
  },

  hasNotificationsIcon: {
    color: '#388E3C',
    alignSelf: 'center',
    fontSize: '50px',
    padding: 10,
  },

  header: {
    borderRadius: 0,
    boxShadow: 'none',
    width: '100%',
    padding: 5,
    display: 'flex',
    flexDirection: 'row',
    alignItems: 'space-between',
  },

  headerText: {
    fontSize: 20,
  },

  noNotificationsCard: {
    borderLeft: '10px solid #31708F',
    marginTop: '15px',
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    justifyContent: 'space-between',
  },

  noNotificationsIcon: {
    color: '#31708F',
    alignSelf: 'center',
    fontSize: '50px',
    padding: 10,
  },

  typographyItalics: {
    display: 'flex',
    fontStyle: 'italic',
    maxWidth: '95%',
    marginLeft: '15.6px',
  },
}

class NotificationsCard extends Component {
  state = {
    expanded: false,
    edit: false,
    notifs: false,
    currentTextNum: '',
    currentCellNum: '',
    currentHomeNum: '',
    newTextNum: '',
    newCellNum: '',
    newHomeNum: '',
    errorUpdating: false,
  }

  static defaultProps = {
    regexp: /^[0-9\b]{1,10}$/,
  }

  componentDidMount() {
    getNumbers().then((n1) => {
      if(n1.length > 0) {
        this.setState({ notifs: true })
        let textNum, cellNum, homeNum
        if (typeof n1 === 'object') {
          n1.forEach((obj) => {
            if (obj.type === 'TEXT') {
              textNum = obj.number
            }
            if (obj.type === 'CELL') {
              cellNum = obj.number
            }
            if (obj.type === 'HOME') {
              homeNum = obj.number
            }
          })
        }
        this.setState({
          currentTextNum: textNum,
          currentCellNum: cellNum,
          currentHomeNum: homeNum,
          newTextNum: textNum,
          newCellNum: cellNum,
          newHomeNum: homeNum,
        })
      }
    })
  }

  handleExpand = () => {
    this.setState({ expanded: !this.state.expanded })
  }

  handleEdit = () => {
    this.setState({ edit: true })
  }

  handleSubmit = () => {
    this.setState({ edit: false })
    this.setState({ notifs: true })
    const { newTextNum, newCellNum, newHomeNum } = this.state

    updateNumbers({ newTextNum, newCellNum, newHomeNum }).then((data) => {
      if(data === null || data === undefined) {
        this.setState({ error: true })
        this.setState({ newTextNum: this.state.currentTextNum })
        this.setState({ newCellNum: this.state.currentCellNum })
        this.setState({ newHomeNum: this.state.currentHomeNum })
      }
    })
  }

  handleErrorClose = () => {
    this.setState({ error: false })
  }

  handlePhoneChange = (phoneType, event) => {
    if (event.target.value === '' || this.props.regexp.test(event.target.value)) {
      this.setState({[phoneType]: event.target.value})
    }
  }

  handleKeyPress = (event) => {
    if (event.key === 'Enter') {
      this.handleSubmit()
    }
  }

  render() {
    const { classes } = this.props
    const { expanded, edit, notifs } = this.state

    return (
      <div className='NotificationsCard'>
        {notifs === true ? (
          <Card className={classes.hasNotificationsCard}>
            <CardHeader
              className={classes.header}
              action={
                <CardActions disableSpacing>
                  <CollapseButton expanded={this.state.expanded} handleExpand={this.handleExpand} />
                </CardActions>
              }
              classes={{
                title: classes.headerText,
              }}
              avatar={<CheckCircleIcon className={classes.hasNotificationsIcon} />}
              title='You are signed up for Emergency Notifications'
            />
            <Collapse in={expanded} timeout='auto' unmountOnExit>
              <InfoText />

              {edit === true ? (
                <div>
                  <Typography variant='caption'>
                    <div className={classes.typographyItalics}>
                      <br />
                      * You may use the same phone number in more than one field.
                      <br />
                      <br />* Key in numbers with no hyphens (ex. 2481234567).
                      <br />
                    </div>
                  </Typography>
                  <InputsList
                    currentTextNum={this.state.newTextNum}
                    currentCellNum={this.state.newCellNum}
                    currentHomeNum={this.state.newHomeNum}
                    handlePhoneChange={this.handlePhoneChange}
                    handleSubmit={this.handleSubmit}
                    handleKeyDown={this.handleKeyPress}
                  />
                </div>
              ) : (
                <NumbersList
                  currentTextNum={this.state.newTextNum}
                  currentCellNum={this.state.newCellNum}
                  currentHomeNum={this.state.newHomeNum}
                  handleEdit={this.handleEdit}
                />
              )}
            </Collapse>
          </Card>
        ) : (
          <Card className={classes.noNotificationsCard}>
            <CardHeader
              className={classes.header}
              action={
                <CardActions disableSpacing>
                  <CollapseButton expanded={this.state.expanded} handleExpand={this.handleExpand} />
                </CardActions>
              }
              classes={{
                title: classes.headerText,
              }}
              avatar={<InfoIcon className={classes.noNotificationsIcon} />}
              title='Sign up for Emergency Notifications'
            />
            <Collapse in={expanded} timeout='auto' unmountOnExit>
              <InfoText />
              <Typography variant='caption'>
                <div className={classes.typographyItalics}>
                  <br />
                  * You may use the same phone number in more than one field.
                  <br />
                  <br />* Key in numbers with no hyphens (ex. 2481234567).
                  <br />
                </div>
              </Typography>
              <InputsList
                currentTextNum={this.state.newTextNum}
                currentCellNum={this.state.newCellNum}
                currentHomeNum={this.state.newHomeNum}
                handlePhoneChange={this.handlePhoneChange}
                handleSubmit={this.handleSubmit}
                handleKeyDown={this.handleKeyPress}
              />
            </Collapse>
          </Card>
        )}
        <ErrorDialog 
          error={this.state.error}
          handleErrorClose={this.handleErrorClose} 
        />
      </div>
    )
  }
}

export default withStyles(styles)(NotificationsCard)
