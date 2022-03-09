# Microsoft Teams - Delete member from group

If you need to remove your user from different groups, and you don't want to do it manually, you can use this application.

The application does these steps:

- Get your user's information (needed to get your Microsoft Teams id).
- Get the groups you are a member of. It filters out the groups with a pattern in the name.
- Finally it removes your user from the filtered groups. If the group is the whitelist, it will not be removed.

```yaml
app:
  services:
    delete-member:
      white-list:
        Grp_T_NAME_Project_1234
```

To run the application, you need to have the following environment variable set:
- `ACCESS_TOKEN`: Your Microsoft Teams access token. You can get it from the Microsoft Graph Explorer.

## Resources:

- [Microsoft Graph Explorer](https://developer.microsoft.com/en-us/graph/graph-explorer)
- [Microsoft Teams API Overview](https://docs.microsoft.com/es-es/graph/api/resources/teams-api-overview?view=graph-rest-1.0)
